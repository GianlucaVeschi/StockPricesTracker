package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.network.scarlet.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocketImpl
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class MainRepositoryImpl(
    private val service: TradeRepublicService,
    private val tradeRepublicWebSocketImpl : TradeRepublicWebSocketImpl
) : MainRepository {


    override fun initTickerObservations() {
        Timber.d("init observation")
        tradeRepublicWebSocketImpl.initOldWebSocket()
    }

    //Not working yet
    fun initObservationUsingScarlet() {
        observeWebSocket()
        observeTicker()
    }

    private fun observeWebSocket() {
        val subscriptionIsin = "{\"subscribe\":\"<US0378331005>\"}"
        service.observeWebSocket()
            .flowOn(Dispatchers.IO)
            .onEach { event ->
                Timber.d("Event = ${event::class.java.simpleName}")
                if (event !is WebSocket.Event.OnMessageReceived) {
                    Timber.d("Event = ${event::class.java.simpleName}")
                }
                if (event is WebSocket.Event.OnConnectionOpened<*>) {
                    Timber.d("Event = ${event::class.java.simpleName}")
                    service.sendSubscribe(subscriptionIsin)
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    private fun observeTicker() {
        service.observeTicker()
            .flowOn(Dispatchers.IO)
            .onEach {
                Timber.d("received ticker : $it")
            }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun subscribeToTicker(tickerInfo: TickerInfo) {
        Timber.d("subscribe To Ticker")
    }

    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
        Timber.d("unSubscribe From Ticker")
    }
}