package com.candidate.android.dev.wongnai_assignment.DI

import com.candidate.android.dev.wongnai_assignment.Data.remote.CoinService
import com.candidate.android.dev.wongnai_assignment.Data.repository.coin.CoinServiceImpl
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun configureNetworkModuleForTest()
        = module{
    factory{ CoinServiceImpl() }
}