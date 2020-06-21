package com.candidate.android.dev.wongnai_assignment.Data.repository.coin

import com.candidate.android.dev.wongnai_assignment.Data.BaseResult
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.Coin

interface CoinRepository{
    suspend fun fetchCoin(prefix:String? = null,page: Int? = 0):BaseResult<Coin>

}