package com.bankingapp.test.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


// Load a image from a url into a imageView
object PhotoUtils {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadimage(view: ImageView, imageUrl: String?) {
            if (imageUrl != null) {
                Picasso.get()
                    .load(imageUrl)
                    .into(view)
            } else {
                view.setImageBitmap(null)
            }
        }
}