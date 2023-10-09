package com.bankingapp.test.ui.movements

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bankingapp.test.R
import com.bankingapp.test.databinding.FragmentMovementsBinding
import com.bankingapp.test.ui.toolbar.ToolbarFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovementFragment: Fragment(), ToolbarFunctions{

    private lateinit var movementPhotoBinding: FragmentMovementsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movementPhotoBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_movements, container, false)

        movementPhotoBinding.titleToolbar = getString(R.string.title_toolbar_movement)
        movementPhotoBinding.toolbarListener = this

        movementPhotoBinding.toolbar.back.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
        movementPhotoBinding.toolbar.titleToolbar.setTextColor(
            ContextCompat.getColor(requireContext(),R.color.white))
        val bundle = arguments
        if (bundle == null) {
            Log.e("Confirmation", "Movement Fragment not receive the arguments")
        }

        val args = bundle?.let { MovementFragmentArgs.fromBundle(it) }
        movementPhotoBinding.movement= args?.movement
        movementPhotoBinding.userBanking = args?.userBanking

        return movementPhotoBinding.root
    }

    //Click back function
    override fun clickBackFunction() {
        findNavController().popBackStack()
    }
}