package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import timber.log.Timber

class MainRepositoryImpl : MainRepository {

    override fun initTickerObservations() {
        Timber.d("init observation")
    }

    override fun subscribeToTicker(tickerInfo: TickerInfo) {
        Timber.d("subscribe To Ticker")
    }

    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
        Timber.d("unSubscribe From Ticker")
    }
}