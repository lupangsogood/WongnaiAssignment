package com.candidate.android.dev.wongnai_assignment.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.candidate.android.dev.wongnai_assignment.Data.repository.coin.CoinServiceImpl

class MainSccrennViewModelFactory(private val viewModel:MainScreenViewModel) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel::class.java)){
            return viewModel as T
        }
        throw IllegalArgumentException("Unknow Viewmodel Class")    }
}