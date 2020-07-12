package com.candidate.android.dev.wongnai_assignment.DI

import androidx.lifecycle.ViewModel
import com.candidate.android.dev.wongnai_assignment.Data.remote.CoinService
import com.candidate.android.dev.wongnai_assignment.Data.remote.RemoteDataSource
import com.candidate.android.dev.wongnai_assignment.Data.repository.coin.CoinServiceImpl
import com.candidate.android.dev.wongnai_assignment.Screen.MainScreen
import com.candidate.android.dev.wongnai_assignment.Screen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val assignmentModule = module {
    single{ CoinServiceImpl()}
    factory { MainScreen() }
    viewModel {MainScreenViewModel(get())}
}