package com.homework.weather.utilities

import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.homework.weather.AppApplication
import com.bumptech.glide.request.target.Target

object ImageUtils {
    fun urlLoadImage(url: String): RequestBuilder<Drawable> {
        val requestOptions = RequestOptions()
        val context = AppApplication.getContext()
        return Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .override(Target.SIZE_ORIGINAL)
    }
}