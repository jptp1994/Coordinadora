package com.bankingapp.test.utils.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.bankingapp.test.R
import com.bankingapp.test.databinding.DialogSelectPhotoBinding

object SelectPhotoDialog {

    fun showDialog(context: Context, listener:SelectPhotoDialogListener) {
            val dialogBuilder = AlertDialog.Builder(context)
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView: View = inflater.inflate(R.layout.dialog_select_photo, null)
            val bindingLayout: DialogSelectPhotoBinding? =
                dialogView.let { DataBindingUtil.bind(it) }
            dialogBuilder.setView(dialogView)
            val alertDialog = dialogBuilder.create()

            val window = alertDialog.window
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )


            bindingLayout?.apply {
                btnGallery.setOnClickListener {
                    listener.selectPhotoFromGallery()
                }
                btnTakePhoto.setOnClickListener {
                    listener.takePhotoClick()
                }
            }
            alertDialog.setCanceledOnTouchOutside(false)

            alertDialog.show()

        }
    }

