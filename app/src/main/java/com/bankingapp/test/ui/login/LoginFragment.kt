package com.bankingapp.test.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bankingapp.domain.model.Response
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentLoginBinding
import com.bankingapp.test.ui.login.listener.LoginListener
import com.bankingapp.test.ui.login.viewmodel.LoginViewModel
import com.bankingapp.test.utils.SecurityUtils.cypherDecrypt
import com.bankingapp.test.utils.SecurityUtils.cypherEncrypt
import com.bankingapp.test.utils.constants.Constants
import com.bankingapp.test.utils.extensionsfunctions.longSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(), LoginListener {

    private lateinit var loginBinding: FragmentLoginBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginBinding.clickHandler = this
        loginBinding.baseClickHandler = this
        subscribeUi()

        return loginBinding.root

    }


    private fun subscribeUi() {
        // handle the login response
        loginViewModel.loginResponse.observe(viewLifecycleOwner){
            when(val userResponse = it) {
                is Response.Loading -> loginViewModel.isLoading.value = true
                is Response.Success ->{
                    loginViewModel.isLoading.value = false
                        // redirect to home fragment if the login is valid
                        userResponse.data?.let { it1 ->
                            val directions=LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                loginViewModel.convertData(it1)
                            )
                            findNavController().navigate(directions)
                        }?: loginBinding.clContainer.longSnackBar(getString(R.string.credentials_incorrect))
                }

                //handle if the service fail
                is Response.Failure -> {
                    loginViewModel.isLoading.value = false
                    loginBinding.clContainer.longSnackBar(userResponse.e.toString())
                }
                else -> {
                //do nothing}
                }
            }
        }

        //handle the progressbar
        loginViewModel.isLoading.observe(viewLifecycleOwner){
            if (it == true) {
              loginBinding.loading.visibility= View.VISIBLE
            } else {
                loginBinding.loading.visibility = View.GONE
            }
        }
    }

    //handle the click to redirect to register
    override fun clickRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_register, null, null)
    }

    //handle the click to redirect to login
    override fun clickAcceptButton() {
        loginViewModel.getUser(loginBinding.edEmail.text.toString(), loginBinding.edCurrentPassword.text.toString())
    }


}