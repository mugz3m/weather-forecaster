package ru.mugz3m.weatherforecaster.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class GlideImageLoader(private val context: Context) {
    fun loadWeatherIconInImageView(iconId: String, imageView: ImageView) {
        val iconUri = "http://openweathermap.org/img/wn/${iconId}@2x.png"
        Glide.with(context).asBitmap().load(iconUri)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap, transition: Transition<in Bitmap>?
                ) {
                    imageView.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit
            })
    }
}
