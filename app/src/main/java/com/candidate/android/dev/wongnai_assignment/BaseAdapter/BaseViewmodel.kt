package com.candidate.android.dev.wongnai_assignment.BaseAdapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candidate.android.dev.wongnai_assignment.Extension.simpleName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel : ViewModel() {


    private fun cancelJob() {
        viewModelScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        cancelJob()
        Timber.w(simpleName(), "$this is Cleared")
        super.onCleared()
    }
}