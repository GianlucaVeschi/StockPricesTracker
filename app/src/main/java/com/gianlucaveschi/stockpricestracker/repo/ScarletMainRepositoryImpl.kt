package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.model.*
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


    override fun initTickerObservations(): Flow<TickerUiModel> {
        Timber.d("init observation")
        initObservationUsingScarlet()
        return flowOf(TickerUiModel(TickerInfo("Apple", "US0378331005"), null))
    }

    override fun subscribeToTicker(tickerInfo: TickerInfo) {
        Timber.d("subscribe To Ticker")
    }

    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
        Timber.d("unSubscribe From Ticker")
    }

    //Scarlet Stuff ...
    fun initObservationUsingScarlet() {
        //val appleTicker = TickerSubscription("US0378331005")
        val appleTicker = ScarletTickerSubscription("US0378331005")
        service.observeOnConnectionOpenedEvent()
            .flowOn(Dispatchers.IO)
            .onEach { event ->
                if (event !is WebSocket.Event.OnMessageReceived) {
                    Timber.d("Event = ${event::class.java.simpleName}")
                }

                if (event is WebSocket.Event.OnConnectionOpened<*>) {
                    service.sendSubscribe(appleTicker)
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))

        service.observeTicker()
            .flowOn(Dispatchers.IO)
            .onEach {
                Timber.d("The price for apple is $it")
            }.launchIn(CoroutineScope(Dispatchers.IO))
    }

}