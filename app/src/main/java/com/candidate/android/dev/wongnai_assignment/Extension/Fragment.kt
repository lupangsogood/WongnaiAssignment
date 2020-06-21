package com.candidate.android.dev.wongnai_assignment.Extension

import android.app.Activity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.candidate.android.dev.wongnai_assignment.R

fun FragmentActivity.setScreenTouchable(touchable: Boolean) {
    when (touchable) {
        true -> window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        false -> window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

fun FragmentActivity.replaceScreen(screen:Fragment){
    val fragmentManger = supportFragmentManager.beginTransaction()
    fragmentManger.replace(R.id.mainContainer,screen)
    fragmentManger.commit()
}

fun Fragment.hideSoftKeyboard() {
    val inputMethodManager: InputMethodManager =
        this.activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
}