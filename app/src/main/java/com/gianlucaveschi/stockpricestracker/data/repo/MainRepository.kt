package com.gianlucaveschi.stockpricestracker.data.repo

import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun observeTicker() : Flow<TickerUiModel>
    fun subscribeToTicker(tickerUiModel: TickerUiModel)
    fun unsubscribeFromTicker(tickerUiModel: TickerUiModel)
}