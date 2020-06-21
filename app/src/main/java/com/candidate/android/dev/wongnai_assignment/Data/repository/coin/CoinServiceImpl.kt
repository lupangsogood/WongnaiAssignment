package com.candidate.android.dev.wongnai_assignment.Data.repository.coin

import com.candidate.android.dev.wongnai_assignment.Data.BaseResult
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.Coin
import com.candidate.android.dev.wongnai_assignment.Data.remote.ApiManager
import com.candidate.android.dev.wongnai_assignment.Data.remote.RemoteDataSource
import com.candidate.android.dev.wongnai_assignment.Data.remote.RemoteDataSource.*
import com.candidate.android.dev.wongnai_assignment.Data.remote.CoinService

open class CoinServiceImpl :CoinRepository,RemoteDataSource(){
    override suspend fun fetchCoin(prefix:String?,page: Int?): BaseResult<Coin> {
        return call(ApiManager.callService.getCoin(offset = page,prefix = prefix))
    }
}