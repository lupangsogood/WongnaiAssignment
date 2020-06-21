package com.candidate.android.dev.wongnai_assignment.Extension

import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.candidate.android.dev.wongnai_assignment.R

fun RecyclerView.slideRtoL(){
    val controller: LayoutAnimationController? = AnimationUtils.loadLayoutAnimation(context, R.anim.anim_slide_r_to_l)
    this.layoutAnimation = controller
    this.scheduleLayoutAnimation()
}