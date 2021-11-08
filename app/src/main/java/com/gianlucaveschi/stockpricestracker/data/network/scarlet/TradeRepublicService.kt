package com.gianlucaveschi.stockpricestracker.data.network.scarlet

import com.gianlucaveschi.stockpricestracker.domain.entities.scarlet.ScarletTickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.scarlet.ScarletTickerUnsubscription
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface TradeRepublicService {
    /**
     * A @Send annotated method takes one parameter.
     * When invoked, it sends an outgoing message to the server.
     * */
    @Send
    fun sendSubscribe(subscribe: ScarletTickerSubscription)

    @Send
    fun stopSubscribe(unsubscribe: ScarletTickerUnsubscription)

    /**
     * A @Receive annotated method returns a stream of incoming messages or events about the current connection state.
     * You need to subscribe to the stream to observe values.
     * */
    @Receive
    fun observeTicker(): Flow<ScarletTickerApiModel>

    @Receive
    fun observeOnConnectionOpenedEvent(): Flow<WebSocket.Event>

}