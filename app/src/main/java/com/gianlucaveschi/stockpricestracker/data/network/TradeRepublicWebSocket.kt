package com.gianlucaveschi.stockpricestracker.data.network

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import kotlinx.coroutines.flow.Flow

interface TradeRepublicWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun startTickerSubscription(tickerSubscription: TickerSubscription)
    fun stopTickerSubscription(tickerUnsubscription: TickerUnsubscription)
}