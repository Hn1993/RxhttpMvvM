package com.base.common.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author anhuang
 * @date 2022/3/25
 * Description :
 */
object GlideUtils {

    fun loadImage(url: Any, imageView: ImageView) {
        Glide.with(imageView.context).load(url).dontAnimate().into(imageView)
    }
}