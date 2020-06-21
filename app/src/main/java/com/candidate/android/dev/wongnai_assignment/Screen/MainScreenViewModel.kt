package com.candidate.android.dev.wongnai_assignment.Screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candidate.android.dev.wongnai_assignment.BaseAdapter.BaseViewModel
import com.candidate.android.dev.wongnai_assignment.Data.BaseResult
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.Coin
import com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel.CoinX
import com.candidate.android.dev.wongnai_assignment.Data.repository.coin.CoinServiceImpl
import com.candidate.android.dev.wongnai_assignment.Extension.simpleName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainScreenViewModel(private val coinService: CoinServiceImpl) : BaseViewModel() {

    private val _coinData = MutableLiveData<ArrayList<CoinX>>()
    val coinData get() = _coinData

    private var prefixChecker: String? = null
    var firstSearch: Boolean? = false
    var index = 0

    init {
        Timber.d("${simpleName()} created")
        CoroutineScope(Dispatchers.IO).launch {
            getCoinData()
        }
    }

    suspend fun getCoinData() {
        setClearSearch()
        setPrefix(null)
        viewModelScope.launch {
            val result = coinService.fetchCoin()
            when (result.isSuccessful()) {
                true -> {
                    clearIndex()
                    _coinData.value = result.data?.data?.coins
                }
                false -> {
                    Timber.d(result.message)
                }
            }
        }
    }

    suspend fun searchCoinData(prefix: String) {
        when (prefix) {
            "" -> {
                setClearSearch()
                getCoinData()
            }
            else -> {
                viewModelScope.launch {
                    val result = coinService.fetchCoin(prefix, page = index)
                    when (result.isSuccessful()) {
                        true -> {
                            setPrefix(prefix)
                            setFirstSearch()
                            _coinData.value = result.data?.data?.coins
                        }
                        false -> {
                            Timber.d(result.message)
                        }
                    }
                }
            }
        }
    }

    suspend fun getNextCoin() {
        index = index.plus(10)
        viewModelScope.launch {
            val result = coinService.fetchCoin(prefix = prefixChecker, page = index)
            when (result.isSuccessful()) {
                true -> {
                    _coinData.value = result.data?.data?.coins
                }
                false -> {
                    Timber.d(result.message)
                }
            }
        }
    }

    private fun clearIndex() {
        index = 0
    }

    private fun setPrefix(prefix: String?) {
        prefixChecker = prefix
    }

    private fun setClearSearch() {
        firstSearch = null
        prefixChecker = null
    }

    private fun setFirstSearch() {
        firstSearch = true
    }

    fun setNonFirstSearch() {
        firstSearch = false
    }

}