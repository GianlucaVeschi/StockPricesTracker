package com.gianlucaveschi.stockpricestracker.network.wslistener

import com.gianlucaveschi.stockpricestracker.domain.model.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.model.TicketSubscription
import com.gianlucaveschi.stockpricestracker.domain.model.TicketUnsubscription
import kotlinx.coroutines.flow.Flow

internal interface TradeRepublicWebSocket {
    fun observeTickerUpdates(): Flow<TickerApiModel>
    fun startTickerSubscription(tickerSubscription: TicketSubscription)
    fun stopTickerSubscription(tickerUnsubscription: TicketUnsubscription)
}