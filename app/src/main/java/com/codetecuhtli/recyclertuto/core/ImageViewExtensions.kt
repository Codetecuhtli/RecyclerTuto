package com.codetecuhtli.recyclertuto.core

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String){
    Glide.with(context)
        .load(url)
        .fitCenter()
        .into(this)
}