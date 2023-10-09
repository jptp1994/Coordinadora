package com.bankingapp.test.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentRegisterFormBinding
import com.bankingapp.test.ui.register.listener.RegisterFormListener
import com.bankingapp.test.ui.register.util.RegisterUtils
import com.bankingapp.test.utils.ToolbarFunctions
import com.bankingapp.test.utils.calendar.CalendarListener
import com.bankingapp.test.utils.calendar.CalendarUtils.pickDateTime
import com.bankingapp.test.utils.constants.Constants
import com.bankingapp.test.utils.extensionsfunctions.isEmptyField
import com.bankingapp.test.utils.extensionsfunctions.isValidEmail
import com.bankingapp.test.utils.extensionsfunctions.longSnackBar
import com.bankingapp.test.utils.extensionsfunctions.validatePassword
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date


// Add listener for buttons in elements and toolbar
@AndroidEntryPoint
class RegisterFormFragment:Fragment(), RegisterFormListener, ToolbarFunctions, CalendarListener {

    // Date variable to set calendar and binding
    private lateinit var dateBirth: Date
    private lateinit var registerFormBinding: FragmentRegisterFormBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerFormBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_register_form, container, false)
        registerFormBinding.clickHandler = this
        registerFormBinding.toolbarListener = this
        registerFormBinding.titleToolbar = getString(R.string.title_toolbar_register_photo)

        registerFormBinding.toolbar.back.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        registerFormBinding.toolbar.titleToolbar.setTextColor(
            ContextCompat.getColor(requireContext(),R.color.white))

        setupListeners()
        return registerFormBinding.root

    }

    private fun setupListeners() = with(registerFormBinding) {
        edName.addTextChangedListener(TextFieldValidation(edName))
        edLastName.addTextChangedListener(TextFieldValidation(edLastName))
        edDateBirth.addTextChangedListener(TextFieldValidation(edDateBirth))
        edAge.addTextChangedListener(TextFieldValidation(edAge))
        edEmail.addTextChangedListener(TextFieldValidation(edEmail))
        edCurrentPassword.addTextChangedListener(TextFieldValidation(edCurrentPassword))
    }

    //Open Calendar
    override fun clickOpenCalendar() {
        pickDateTime(this, requireContext())
    }

    // Click accept button
    override fun clickAcceptButton() = with(registerFormBinding) {
        if (edName.error.isNullOrEmpty() && edLastName.error.isNullOrEmpty() &&
            edAge.error.isNullOrEmpty() && edDateBirth.text.toString().isNotEmpty()
            && edEmail.error.isNullOrEmpty()&& edCurrentPassword.error.isNullOrEmpty()) {
            val directions =
                RegisterFormFragmentDirections.actionRegisterFragmentToRegisterPhotoFragment(
                    RegisterUtils.createUserModel(
                        name= edName.text.toString(),
                        lastName= edLastName.text.toString(),
                        age= edAge.text.toString(),
                        email= edEmail.text.toString(),
                        password= edCurrentPassword.text.toString(),
                        dateBirth= dateBirth)
                )
            findNavController().navigate(directions)
        } else{
           materialCardView.longSnackBar(Constants.errorBtnRegister)
        }
    }

    //click back button toolbar
    override fun clickBackFunction() {
        findNavController().popBackStack()
    }

    /**
     * applying text watcher on each text field
     */
    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
            with(registerFormBinding) {
                // checking ids of each text field and applying functions accordingly.
                when (view.id) {
                    R.id.ed_name -> {
                        edName.error = if (edName.text.toString().isEmptyField()){
                           Constants.errorFirstName
                        } else{
                         null
                        }
                    }
                    R.id.ed_lastName -> {
                        edLastName.error =
                            if (edLastName.text.toString().isEmptyField()){
                                Constants.errorLastName
                            } else{
                                null
                            }
                    }

                    R.id.ed_age -> {
                        edAge.error =
                            if (edAge.text.toString().isEmptyField()){
                                Constants.errorAge
                            } else{
                                null
                            }
                    }

                    R.id.ed_dateBirth -> {
                        edDateBirth.error =
                            if (edDateBirth.text.toString().isEmptyField()){
                                Constants.errorDateBirth
                            } else{
                                null
                            }
                    }

                    R.id.ed_email -> {
                        edEmail.error =
                            if (!edEmail.text.toString().isValidEmail()){
                                Constants.errorEmailField
                            } else{
                                null
                            }
                    }

                    R.id.ed_current_password -> {
                        edCurrentPassword.error =
                            if (!edCurrentPassword.text.toString().validatePassword()){
                                Constants.errorPasswordField
                            } else{
                                null
                            }
                    }
                }
            }
    }

    override fun setDate(convertFormatFull: String, time: Date) {
        registerFormBinding.edDateBirth.setText(convertFormatFull)
        dateBirth = time
    }

}