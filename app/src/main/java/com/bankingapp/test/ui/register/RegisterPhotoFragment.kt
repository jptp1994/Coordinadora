package com.bankingapp.test.ui.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bankingapp.domain.model.Response
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentRegisterPhotoBinding
import com.bankingapp.test.ui.login.model.User
import com.bankingapp.test.ui.register.listener.RegisterPhotoListener
import com.bankingapp.test.ui.register.viewmodel.RegisterViewModel
import com.bankingapp.test.utils.ToolbarFunctions
import com.bankingapp.test.utils.constants.Constants
import com.bankingapp.test.utils.extensionsfunctions.longSnackBar
import com.bankingapp.test.utils.extensionsfunctions.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class RegisterPhotoFragment: Fragment(), RegisterPhotoListener, ToolbarFunctions {

    private lateinit var registerPhotoBinding: FragmentRegisterPhotoBinding

    private val registerViewModel by viewModels<RegisterViewModel>()

    private lateinit var photoFile: File
    private lateinit var userData: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerPhotoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register_photo, container, false)
        registerPhotoBinding.clickHandler = this
        registerPhotoBinding.viewModel = registerViewModel
        registerPhotoBinding.toolbarListener = this
        registerPhotoBinding.titleToolbar = getString(R.string.title_toolbar_register_photo)

        registerPhotoBinding.toolbar.back.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        registerPhotoBinding.toolbar.titleToolbar.setTextColor(
            ContextCompat.getColor(requireContext(),R.color.white))

        val bundle = arguments
        val args = bundle?.let { RegisterPhotoFragmentArgs.fromBundle(it) }
        args?.user?.let {
            userData= it
        }
        subscribeUI()
        registerViewModel.getLastUser()
        return registerPhotoBinding.root
    }

    override fun clickTakePhotoButton() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                openCamera()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
            -> {
                requireContext().showToast(getString(R.string.error_permissions_camera_denied))
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }

        }

    }

    private fun openCamera(){
        val takePictureIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile= getPhotoFile(userData.name+userData.lastName+userData.lastConnection)
        val fileProvider=FileProvider.getUriForFile(requireContext(),
            Constants.authorities,photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider)
        takePictureLauncher.launch(takePictureIntent)

    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openCamera()
               } else {
                requireContext().showToast(getString(R.string.no_permissions_camera))
            }
        }


    // Set up the ActivityResultLauncher to handle the camera intent result
    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                    val cameraImageTaked=BitmapFactory.decodeFile(photoFile.absolutePath)
                    registerPhotoBinding.imvProfilePicture.setImageBitmap(cameraImageTaked)
                    userData.imageUser = photoFile.absolutePath
                }
            }
    private fun getPhotoFile(fileName: String): File {
        val storageDir= requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storageDir)
    }

    private fun subscribeUI() {
        registerViewModel.lastUserResponse.observe(viewLifecycleOwner) {
            when (val userResponse = it) {
                is Response.Loading -> registerViewModel.isLoading.value = true
                is Response.Success -> {
                    registerViewModel.isLoading.value = false
                    // get the last id user and get an new one
                    userData.idUser = userResponse.data + 1
                }
                //handle if the service fail
                is Response.Failure -> {
                    registerViewModel.isLoading.value = false
                    registerPhotoBinding.clContainer.longSnackBar(userResponse.e.toString())
                }

                else -> {
                    //do nothing}
                }
            }
        }

        //handle the progressbar
        registerViewModel.isLoading.observe(viewLifecycleOwner){
            if (it == true) {
                registerPhotoBinding.loading.visibility= View.VISIBLE
            } else {
                registerPhotoBinding.loading.visibility = View.GONE
            }
        }
        registerViewModel.createUserResponse.observe(viewLifecycleOwner){
            when (val userResponse = it) {
                is Response.Loading -> registerViewModel.isLoading.value = true
                is Response.Success -> {
                    registerViewModel.isLoading.value = false
                    val directions=RegisterPhotoFragmentDirections.actionRegisterPhotoFragmentToHomeFragment(
                        userData
                    )
                    findNavController().navigate(directions)
                }
                //handle if the service fail
                is Response.Failure -> {
                    registerViewModel.isLoading.value = false
                    registerPhotoBinding.clContainer.longSnackBar(userResponse.e.toString())
                }
                else -> {
                    //do nothing}
                }
            }
        }

    }

    override fun clickAcceptButton() {
        if (userData.imageUser.isNotEmpty()) {
            registerViewModel.createUser(userData)
        } else {
            requireContext().showToast(getString(R.string.error_no_photo))
        }
    }


    override fun clickBackFunction() {
        findNavController().popBackStack()
    }
}