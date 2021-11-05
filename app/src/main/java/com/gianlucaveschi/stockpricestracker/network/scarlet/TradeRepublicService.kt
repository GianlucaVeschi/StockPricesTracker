package com.gianlucaveschi.stockpricestracker.network.scarlet

import com.gianlucaveschi.stockpricestracker.domain.model.TicketSubscription
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface TradeRepublicService {

    @Receive
    fun observeWebSocket(): Flow<WebSocket.Event>

    @Send
    fun sendSubscribe(subscription: String)

    @Receive
    fun observeTicker(): Flow<String>
}