package com.candidate.android.dev.wongnai_assignment.BaseAdapter

import android.view.View
import androidx.fragment.app.Fragment
import com.candidate.android.dev.wongnai_assignment.Extension.setScreenTouchable
import kotlinx.android.synthetic.main.fragment_main.*

open class BaseScreen : Fragment(){

    fun showLoading(){
        progress.visibility = View.VISIBLE
        this.activity?.setScreenTouchable(false)
    }

    fun hideLoading(){
        progress.visibility = View.GONE
        this.activity?.setScreenTouchable(true)
    }
}