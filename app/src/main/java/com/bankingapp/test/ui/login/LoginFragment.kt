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
import com.bankingapp.domain.model.UserEntity
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentLoginBinding
import com.bankingapp.test.ui.login.listener.LoginListener
import com.bankingapp.test.ui.login.viewmodel.LoginViewModel
import com.bankingapp.test.utils.extensionsfunctions.longSnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class LoginFragment: Fragment(), LoginListener {

    private lateinit var loginBinding: FragmentLoginBinding

    private lateinit var userData:UserEntity
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
                            userData= it1
                            userData.idUser?.let { it2 -> loginViewModel.updateConnection(it2, Calendar.getInstance().time) }
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

        // handle the login response
        loginViewModel.updateConnection.observe(viewLifecycleOwner){
            when(val userResponse = it) {
                is Response.Loading -> loginViewModel.isLoading.value = true
                is Response.Success ->{
                    redirectToHome()
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
    }
    private fun redirectToHome(){
        val directions=LoginFragmentDirections.actionLoginFragmentToHomeFragment(
            loginViewModel.convertData(userData)
        )
        findNavController().navigate(directions)
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