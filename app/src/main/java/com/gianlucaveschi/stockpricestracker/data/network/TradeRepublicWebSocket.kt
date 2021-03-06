package com.gianlucaveschi.stockpricestracker.data.network

import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import kotlinx.coroutines.flow.Flow

interface TradeRepublicWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun subscribeToTicker(tickerSubscription: TickerSubscription)
    fun unsubscribeFromTicker(tickerUnsubscription: TickerUnsubscription)
}