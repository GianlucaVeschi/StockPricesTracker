package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.mapper.MapperTickerApiModelToTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.model.*
import com.gianlucaveschi.stockpricestracker.network.scarlet.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocket
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@ExperimentalSerializationApi
class MainRepositoryImpl(
    private val service: TradeRepublicService,
    private val tradeRepublicWebSocket: TradeRepublicWebSocket,
    private val mapperTickerApiModelToTickerUiModel: MapperTickerApiModelToTickerUiModel
) : MainRepository {


    override fun initTickerObservations(): Flow<TickerUiModel> {
        Timber.d("init observation")
        return tradeRepublicWebSocket.observeTickerUpdates().map {
            mapperTickerApiModelToTickerUiModel.map(it)
        }
    }

    override fun subscribeToTicker(tickerInfo: TickerInfo) {
        Timber.d("subscribe To Ticker")
        val ticketSubscription = TickerSubscription(tickerInfo.isin)
        tradeRepublicWebSocket.startTickerSubscription(ticketSubscription)
    }

    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
        Timber.d("unSubscribe From Ticker")
        val tickerUnsubscription = TickerUnsubscription(tickerInfo.isin)
        tradeRepublicWebSocket.stopTickerSubscription(tickerUnsubscription)
    }

}