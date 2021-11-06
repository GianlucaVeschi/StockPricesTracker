package com.gianlucaveschi.stockpricestracker.data.repo

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerInfo
import com.gianlucaveschi.stockpricestracker.domain.entities.TickerUiModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun initTickerObservations() : Flow<TickerUiModel>
    fun subscribeToTicker(tickerInfo: TickerInfo)
    fun unsubscribeFromTicker(tickerInfo: TickerInfo)
}