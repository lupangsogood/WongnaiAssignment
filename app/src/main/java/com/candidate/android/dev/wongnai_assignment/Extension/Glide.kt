package com.candidate.android.dev.wongnai_assignment.Extension

import android.net.Uri
import android.widget.ImageView

import com.candidate.android.dev.wongnai_assignment.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import timber.log.Timber

fun ImageView.loadFromURLSVG(url: String) {
    GlideToVectorYou
        .init()
        .with(context)
        .withListener(object : GlideToVectorYouListener {
            override fun onLoadFailed() {
            }
            override fun onResourceReady() {
            }
        })
        .setPlaceHolder(R.drawable.ic_outline_image_24, R.drawable.ic_outline_broken_image_24)
        .load(Uri.parse(url), this)
}
