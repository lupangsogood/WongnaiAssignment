package com.candidate.android.dev.wongnai_assignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.candidate.android.dev.wongnai_assignment.BaeUTTest.BaseUTTest
import com.candidate.android.dev.wongnai_assignment.DI.assignmentModule
import com.candidate.android.dev.wongnai_assignment.DI.configureTestAppComponent
import com.candidate.android.dev.wongnai_assignment.Data.BaseResult
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.Coin
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.CoinX
import com.candidate.android.dev.wongnai_assignment.Data.repository.coin.CoinServiceImpl
import com.candidate.android.dev.wongnai_assignment.Screen.MainScreenViewModel
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class getCoinUnitTest : BaseUTTest(){

    val baseApi = "https://api.coinranking.com/v1/public/"

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val coinServiceImpl : CoinServiceImpl by inject()

    init {
        Dispatchers.Main
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        startKoin { modules(configureTestAppComponent()) }
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `should return true when data == offset and total`() = runBlocking {
        val dataReceived = coinServiceImpl.fetchCoin(page = 0)
        assertNotNull(dataReceived)
        assertEquals(0, dataReceived.data?.data?.stats?.offset)
        assertEquals(10387,dataReceived.data?.data?.stats?.total )
    }
}