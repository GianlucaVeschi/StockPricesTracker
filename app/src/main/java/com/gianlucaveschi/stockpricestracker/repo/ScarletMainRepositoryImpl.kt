package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.*
import com.gianlucaveschi.stockpricestracker.domain.model.scarlet.ScarletTickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.model.scarlet.ScarletTickerUnsubscription
import com.gianlucaveschi.stockpricestracker.network.scarlet.TradeRepublicService
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@ExperimentalSerializationApi
class ScarletMainRepositoryImpl(
    private val service: TradeRepublicService,
) : MainRepository {

    override fun subscribeToTicker(tickerInfo: TickerInfo) {
        //Convert TickerInfo to Subscription format
        val scarletTicker = ScarletTickerSubscription(tickerInfo.isin)

        service.observeOnConnectionOpenedEvent()
            .flowOn(Dispatchers.IO)
            .onEach { event ->
                if (event is WebSocket.Event.OnConnectionOpened<*>) {
                    service.sendSubscribe(scarletTicker)
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))

        service.observeTicker()
            .flowOn(Dispatchers.IO)
            .onEach {
                Timber.d("The price for ${tickerInfo.name} is ${it.price}")
            }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
        //Convert TickerInfo to Unsubscription format
        val scarletTicker = ScarletTickerUnsubscription(tickerInfo.isin)
    }
}