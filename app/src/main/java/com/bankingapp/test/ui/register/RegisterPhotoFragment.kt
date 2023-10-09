package com.bankingapp.test.ui.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentRegisterPhotoBinding
import com.bankingapp.test.ui.register.listener.RegisterPhotoListener
import com.bankingapp.test.ui.register.viewmodel.RegisterViewModel
import com.bankingapp.test.ui.toolbar.ToolbarFunctions
import com.bankingapp.test.utils.dialog.SelectPhotoDialog.showDialog
import com.bankingapp.test.utils.dialog.SelectPhotoDialogListener
import com.bankingapp.test.utils.extensionsfunctions.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterPhotoFragment: Fragment(), RegisterPhotoListener, SelectPhotoDialogListener, ToolbarFunctions {

    private val STORAGE_PERMISSION_CODE: Int =99
    private var flagGallery:Boolean =false
    private lateinit var registerPhotoBinding: FragmentRegisterPhotoBinding

    private val registerViewModel by viewModels<RegisterViewModel>()

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

        registerPhotoBinding.toolbar.back.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        registerPhotoBinding.toolbar.titleToolbar.setTextColor(
            ContextCompat.getColor(requireContext(),R.color.white))

        //TODO: validar para migrar logica de camara y almacenamiento para una clase distinta que se encargue de ello
        val bundle = arguments
        if (bundle == null) {
            Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
        }

        val args = bundle?.let { RegisterPhotoFragmentArgs.fromBundle(it) }
        return registerPhotoBinding.root
    }

    // Set up the ActivityResultLauncher to handle the camera intent result
    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val uri: Uri? = data?.data
                if (uri != null) {
                    registerPhotoBinding.imageView2.setImageURI(uri)
  //                  binding?.ivProof?.setImageURI(uri)
                } else {
                    val photo: Bitmap? = data?.extras?.get("data") as? Bitmap
                    if (photo != null) {
                        registerPhotoBinding.imageView2.setImageBitmap(photo)
                        // Handle the captured bitmap
                    }
                }
            }
        }


    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureLauncher.launch(cameraIntent)
    }

    override fun clickTakePhotoButton() {
        showDialog(requireContext(),this)
    }


    override fun clickAcceptButton() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun takePhotoClick() {
        flagGallery = false
        askPermissionCamera()
   //     askPermissionDevice()
    }

    override fun selectPhotoFromGallery() {
        flagGallery = true
        askPermissionDevice()
    }

    private fun askPermissionCamera(){
        when{
            ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED -> openCamera()
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)->
                requireContext().showToast(getString(R.string.rejected_permission_camera))
            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun askPermissionDevice(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE
                )
                        == PackageManager.PERMISSION_GRANTED -> {
                    validateFlagGallery()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.MANAGE_EXTERNAL_STORAGE) ->
                    requireContext().showToast(getString(R.string.rejected_permission_storage))

                else -> requestPermissionDeviceLauncher.launch(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            }
        } else{
            //Below android 11

            //Below android 11
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf<String>(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                STORAGE_PERMISSION_CODE
            )
        }
    }
    private fun validateFlagGallery(){
        if (flagGallery){
            getPhoto()
        } else{
            askPermissionCamera()
        }
    }

    private fun getPhoto(){

    }

     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<out String>,
         grantResults: IntArray
     ) {
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty()) {
                val write = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val read = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (read && write) {
                    Toast.makeText(
                        requireActivity(),
                        "Storage Permissions Granted",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Storage Permissions Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openCamera()
            } else {
                requireContext().showToast(getString(R.string.rejected_permission_camera))
            }
        }


    private val requestPermissionDeviceLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                validateFlagGallery()
            } else {
                requireContext().showToast(getString(R.string.rejected_permission_storage))
            }
        }

    override fun clickBackFunction() {
        findNavController().popBackStack()
    }
}