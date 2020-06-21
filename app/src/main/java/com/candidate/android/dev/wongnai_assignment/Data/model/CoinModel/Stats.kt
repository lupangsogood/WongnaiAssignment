package com.candidate.android.dev.wongnai_assignment.Data.model.CoinModel

data class Stats(
    var base: String? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var order: String? = null,
    var total: Int? = null,
    var total24hVolume: Double? = null,
    var totalExchanges: Int? = null,
    var totalMarketCap: Double? = null,
    var totalMarkets: Int? = null
)