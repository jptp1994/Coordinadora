package com.bankingapp.test.utils.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.bankingapp.test.R
import com.bankingapp.test.databinding.DialogGeneralBinding

object GeneralDialog {

    fun showAlertDialog(context: Context, title:String, subtitle:String, okButton:String,
                        cancelButton:String,listener: DialogListener) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_general, null)
        val bindingLayout: DialogGeneralBinding? = dialogView.let { DataBindingUtil.bind(it) }
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()

        val window = alertDialog.window
        window?.setGravity(Gravity.BOTTOM)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        bindingLayout?.apply {
            tvTitleDialogPhoto.text = title
            tvSubtitleDialog.text = subtitle
            btnCancelButton.text = cancelButton
            btnAccept.text = okButton

            btnCancelButton.setOnClickListener {
                alertDialog.dismiss()
                listener.cancelButton()
            }
            btnAccept.setOnClickListener {
                alertDialog.dismiss()
                listener.confirmButton()
            }
        }

        alertDialog.setCanceledOnTouchOutside(false)

        alertDialog.show()
    }

    /* fun showCustomDialog(
         context: Context?, title: String?, message: String?,
         positiveText: String?, negativeText: String?,key: String
     ) {
         context?.let {
             androidx.appcompat.app.AlertDialog.Builder(it)
                 .setIcon(android.R.drawable.ic_dialog_alert)
                 .setTitle(title)
                 .setMessage(message)
                 .setPositiveButton(
                     positiveText
                 ) { _, _ -> dialogListener.onPositiveDialogText(key) }
                 .setNegativeButton(
                     negativeText
                 ) { _, _ -> dialogListener.onNegativeDialogText(key) }
                 .show()
         }

     }*/
}