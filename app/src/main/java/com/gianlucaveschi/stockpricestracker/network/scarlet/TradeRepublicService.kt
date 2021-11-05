package com.gianlucaveschi.stockpricestracker.network.scarlet

import com.gianlucaveschi.stockpricestracker.domain.model.ScarletTickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.model.ScarletTickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.model.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.model.TickerSubscription
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface TradeRepublicService {
    /**
     * A @Send annotated method takes one parameter.
     * When invoked, it sends an outgoing message to the server.
     * */
    @Send
    fun sendSubscribe(subscribe: ScarletTickerSubscription)

    /**
     * A @Receive annotated method returns a stream of incoming messages or events about the current connection state.
     * You need to subscribe to the stream to observe values.
     * */
    @Receive
    fun observeTicker(): Flow<ScarletTickerApiModel>

    @Receive
    fun observeOnConnectionOpenedEvent(): Flow<WebSocket.Event>

}