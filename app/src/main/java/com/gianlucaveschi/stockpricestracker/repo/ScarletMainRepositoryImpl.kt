package com.gianlucaveschi.stockpricestracker.repo

//import androidx.lifecycle.Transformations.map
//import com.gianlucaveschi.stockpricestracker.domain.getHardcodedTickerInfo
//import com.gianlucaveschi.stockpricestracker.domain.mapper.MapperTickerApiModelToTickerUiModel
//import com.gianlucaveschi.stockpricestracker.domain.model.*
//import com.gianlucaveschi.stockpricestracker.network.scarlet.TradeRepublicService
//import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocket
//import com.tinder.scarlet.WebSocket
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.*
//import kotlinx.serialization.ExperimentalSerializationApi
//import timber.log.Timber

//@ExperimentalSerializationApi
//class ScarletMainRepositoryImpl(
//    private val service: TradeRepublicService,
//    private val mapperTickerApiModelToTickerUiModel: MapperTickerApiModelToTickerUiModel
//) : MainRepository {
//
//
//    override fun initTickerObservations(): Flow<TickerUiModel> {
//        Timber.d("init observation")
//        observeWebSocket()
//        return flowOf(TickerUiModel(TickerInfo("Apple","US0378331005"), null))
//    }
//
//    override fun subscribeToTicker(tickerInfo: TickerInfo) {
//        Timber.d("subscribe To Ticker")
//        val ticketSubscription = TickerSubscription(tickerInfo.isin)
//    }
//
//    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
//        Timber.d("unSubscribe From Ticker")
//        val tickerUnsubscription = TickerUnsubscription(tickerInfo.isin)
//    }
//
//    //Scarlet Stuff ...
//    fun initObservationUsingScarlet() {
//        //observeWebSocket()
//        //observeTicker()
//    }
//
//    private fun observeWebSocket() {
//        val subscriptionIsin = "{\"subscribe\":\"<US0378331005>\"}"
//        val ticketSubscription = TickerSubscription("US0378331005")
//        service.observeWebSocket()
//            .flowOn(Dispatchers.IO)
//            .onEach { event ->
//                Timber.d("Event = ${event::class.java.simpleName}")
//                if (event !is WebSocket.Event.OnMessageReceived) {
//                    Timber.d("Event = ${event::class.java.simpleName}")
//                }
//                if (event is WebSocket.Event.OnConnectionOpened<*>) {
//                    Timber.d("Event = ${event::class.java.simpleName}")
//                    service.sendSubscribe(subscriptionIsin)
//                }
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//    }
//
//    private fun observeTicker() {
//        service.observeTicker()
//            .flowOn(Dispatchers.IO)
//            .onEach {
//                Timber.d("received ticker : $it")
//            }.launchIn(CoroutineScope(Dispatchers.IO))
//    }
//
//}