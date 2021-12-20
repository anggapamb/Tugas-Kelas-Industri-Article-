package com.anggapambudi.articleapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.anggapambudi.articleapp.R
import com.bumptech.glide.Glide

class ViewBindingHelper {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl", "imageThumbnail"], requireAll = false)
        fun loadImage(view: ImageView, imageUrl: String?, imageThumbnail: String?) {

            imageUrl?.let {

                //get thumbnail
                val thumbnail = Glide
                    .with(view.context)
                    .load(imageThumbnail)

                //get image article
                Glide
                    .with(view.context)
                    .load(imageUrl)
                    .thumbnail(thumbnail)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.noimage)
                    .into(view)
            }

        }
    }
}