package com.gianlucaveschi.stockpricestracker.network.wslistener

import com.gianlucaveschi.stockpricestracker.domain.model.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.model.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.model.TickerUnsubscription
import kotlinx.coroutines.flow.Flow

interface TradeRepublicWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun startTickerSubscription(tickerSubscription: TickerSubscription)
    fun stopTickerSubscription(tickerUnsubscription: TickerUnsubscription)
}