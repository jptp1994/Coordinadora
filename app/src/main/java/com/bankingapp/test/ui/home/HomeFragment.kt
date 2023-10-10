package com.bankingapp.test.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bankingapp.domain.model.Response
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentHomeBinding
import com.bankingapp.test.ui.home.adapter.RowMovementAdapter
import com.bankingapp.test.ui.home.model.HomeResponse
import com.bankingapp.test.ui.home.viewmodel.HomeViewModel
import com.bankingapp.test.ui.movements.model.Movement
import com.bankingapp.test.utils.ToolbarFunctions
import com.bankingapp.test.utils.dialog.DialogListener
import com.bankingapp.test.utils.dialog.GeneralDialog.showAlertDialog
import com.bankingapp.test.utils.extensionsfunctions.convertFormatFull
import com.bankingapp.test.utils.extensionsfunctions.formatAmount
import com.bankingapp.test.utils.extensionsfunctions.longSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment:Fragment(), ToolbarFunctions, DialogListener {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var listAdapter:RowMovementAdapter

    private var amount = 0.0
    private lateinit var homeResponse:HomeResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeFragmentBinding.toolbarListener = this
        val bundle = arguments
        if (bundle == null) {
            Log.e("Confirmation", "HomeFragment did not receive user information")
        }

        val args = bundle?.let { HomeFragmentArgs.fromBundle(it) }
        homeFragmentBinding.nameUser = args?.user?.name + " " + args?.user?.lastName
        homeFragmentBinding.lastConnection =  args?.user?.lastConnection?.convertFormatFull()

        listAdapter = RowMovementAdapter(::redirectToMovementFragment)

        homeFragmentBinding.rvMovements.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        homeFragmentBinding.rvMovements.adapter = listAdapter

        amount = 0.0

        val cameraImageTaken= BitmapFactory.decodeFile(args?.user?.imageUser)
        homeFragmentBinding.toolbarHome.ivProfile.setImageBitmap(cameraImageTaken)

        args?.user?.idUser?.let {
            homeViewModel.getData(it)
        }
        subscribeUi()

        return homeFragmentBinding.root

    }

    private fun redirectToMovementFragment(it: Movement) {
       val data= homeResponse.bankingList.first { bankingData ->
            it.idAccount == bankingData.bankNumber
        }
        val directions =
            HomeFragmentDirections.homeToMovements(
                it,data
            )
        findNavController().navigate(directions)
    }



    private fun subscribeUi() {
        //state for the home response
        homeViewModel.homeResponse.observe(viewLifecycleOwner){
            when(val userResponse = it) {
                is Response.Loading -> homeViewModel.isLoading.value = true
                is Response.Success ->{
                    homeViewModel.isLoading.value = false
                    userResponse.data?.let { response ->
                        homeResponse = homeViewModel.convertData(response)

                        //mock the data for the adapter
                        val movementList= arrayListOf<Movement>()
                        homeResponse.bankingList.forEach {
                            bankingData->
                                movementList.addAll(bankingData.movementEntities)
                                bankingData.movementEntities.forEach {
                                    movement->
                                    amount+= movement.amount
                                }
                        }
                        // set the amount of balance
                        homeFragmentBinding.amount = "$${amount.formatAmount()}"
                        //send the list of movements
                        listAdapter.submitList(movementList)
                    }?: homeFragmentBinding.clContainer.longSnackBar(getString(R.string.no_movements_error))
                }

                //Handle the response if the service failure
                is Response.Failure -> {
                    homeViewModel.isLoading.value = false
                    homeFragmentBinding.clContainer.longSnackBar(userResponse.e.toString())
                }
                else -> {
                    //do nothing}
                }
            }
        }

        // state for handle progressbar
        homeViewModel.isLoading.observe(viewLifecycleOwner){
            if (it == true) {
                homeFragmentBinding.loading.visibility= View.VISIBLE
            } else {
                homeFragmentBinding.loading.visibility = View.GONE
            }
        }
    }

    // function to logout button
    override fun clickBackFunction() {
        showAlertDialog(context=requireContext(),
            title= getString(R.string.title_dialog_home),
            subtitle= getString(R.string.subtitle_dialog_home),
            okButton = getString(R.string.yes),
            cancelButton = getString(R.string.no),
            this)
    }

    override fun confirmButton() {
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment, null, null)

    }

    override fun cancelButton() {
        homeFragmentBinding.clContainer.longSnackBar(getString(R.string.close_dialog_logout))
    }


}