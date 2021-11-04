package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.domain.model.Ticker
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun initTickerObservations()
    fun subscribeToTicker(tickerInfo: TickerInfo)
    fun unsubscribeFromTicker(tickerInfo: TickerInfo)
}