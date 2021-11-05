package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.domain.model.TickerUiModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun initTickerObservations() : Flow<TickerUiModel>
    fun subscribeToTicker(tickerInfo: TickerInfo)
    fun unsubscribeFromTicker(tickerInfo: TickerInfo)
}