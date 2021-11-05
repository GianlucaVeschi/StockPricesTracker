package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo

interface MainRepository {

    fun initTickerObservations()
    fun subscribeToTicker(tickerInfo: TickerInfo)
    fun unsubscribeFromTicker(tickerInfo: TickerInfo)
}