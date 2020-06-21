package com.candidate.android.dev.wongnai_assignment.Data.remote

import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.Coin
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {
    @GET("coins/")
    suspend fun getCoin(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 10,
        @Query("prefix") prefix:String? =null
    ): Coin
}