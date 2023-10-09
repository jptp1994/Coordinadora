package com.bankingapp.test.ui.register

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentRegisterFormBinding
import com.bankingapp.test.ui.login.model.User
import com.bankingapp.test.ui.register.listener.RegisterFormListener
import com.bankingapp.test.ui.register.viewmodel.RegisterViewModel
import com.bankingapp.test.ui.toolbar.ToolbarFunctions
import com.bankingapp.test.utils.constants.Constants
import com.bankingapp.test.utils.extensionsfunctions.convertFormatFull
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date


// Add listener for buttons in elements and toolbar
@AndroidEntryPoint
class RegisterFormFragment:Fragment(), RegisterFormListener, ToolbarFunctions {

    // Date variable to set calendar and binding
    private lateinit var dateBirth: Date
    private lateinit var registerFormBinding: FragmentRegisterFormBinding

    private val registerViewModel by viewModels<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerFormBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_register_form, container, false)
        registerFormBinding.clickHandler = this
        registerFormBinding.toolbarListener = this
        registerFormBinding.viewModel = registerViewModel
        registerFormBinding.titleToolbar = getString(R.string.title_toolbar_register_photo)

        registerFormBinding.toolbar.back.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        registerFormBinding.toolbar.titleToolbar.setTextColor(
            ContextCompat.getColor(requireContext(),R.color.white))

        subscribeUi()
        return registerFormBinding.root

    }



    //Method to set error fields
    private fun subscribeUi() = with(registerFormBinding){
        registerViewModel.firstNameValidText.observe(viewLifecycleOwner){
            tiName.error= it
        }
        registerViewModel.lastNameValidText.observe(viewLifecycleOwner){
            tiLastName.error = it
        }
        registerViewModel.ageNameValidText.observe(viewLifecycleOwner){
            tiAge.error= it
        }
        registerViewModel.emailValidText.observe(viewLifecycleOwner) {
            tiEmail.error = it
        }
        registerViewModel.passwordValidText.observe(viewLifecycleOwner) {
            tiPassword.error = it
        }
    }


    //TODO: validar para migrar calendario a una funcion externa
    override fun clickOpenCalendar() {
        pickDateTime()
    }


    // Click accept button
    override fun clickAcceptButton() = with(registerFormBinding) {
        //TODO: validar para mejorar validacion
        if (edName.error.isNullOrEmpty() &&
            edLastName.error.isNullOrEmpty() && edAge.error.isNullOrEmpty() && edDateBirth.text.toString().isNotEmpty()
            && edEmail.error.isNullOrEmpty()&& edCurrentPassword.error.isNullOrEmpty()) {
            val directions =
                RegisterFormFragmentDirections.actionRegisterFragmentToRegisterPhotoFragment(
                    User(
                        idUser = "",
                        name = edName.text.toString(),
                        lastName = edLastName.text.toString(),
                        age = edAge.text.toString(),
                        email = edEmail.text.toString(),
                        password = edCurrentPassword.text.toString(),
                        dateBirth = dateBirth,
                        lastConnection = Calendar.getInstance().time,
                        imageUser = ""
                    )
                )

            //TODO: validate fields and create material date picker dialog to finish the flow
            findNavController().navigate(directions)
        } else{
            //TODO: crear extension para snackbar
            Snackbar.make(materialCardView, Constants.errorBtnRegister, Snackbar.LENGTH_LONG).show()
        }
    }

    //click back button toolbar
    override fun clickBackFunction() {
        findNavController().popBackStack()
    }

    //Open calendar
    private fun pickDateTime(){
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(requireContext(), { _, year, month, day ->
            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    val pickedDateTime = Calendar.getInstance()
                    pickedDateTime.set(year, month, day, hour, minute)
                    registerFormBinding.edDateBirth.setText(pickedDateTime.time.convertFormatFull())
                    dateBirth = pickedDateTime.time
                },
                startHour,
                startMinute,
                false
            ).show()
        }, startYear, startMonth, startDay)
            .show()
    }

}