package com.gianlucaveschi.stockpricestracker.repo

import com.gianlucaveschi.stockpricestracker.domain.mapper.mapToTicketSubscription
import com.gianlucaveschi.stockpricestracker.domain.mapper.mapToTicketUnsubscription
import com.gianlucaveschi.stockpricestracker.domain.mapper.mapToUiModel
import com.gianlucaveschi.stockpricestracker.domain.model.*
import com.gianlucaveschi.stockpricestracker.network.scarlet.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocket
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@ExperimentalSerializationApi
class MainRepositoryImpl(
    private val service: TradeRepublicService,
    private val tradeRepublicWebSocket: TradeRepublicWebSocket
) : MainRepository {


    override fun initTickerObservations(): Flow<TickerUiModel> {
        Timber.d("init observation")
        return tradeRepublicWebSocket.observeTickerUpdates().map { it.mapToUiModel() }
    }

    override fun subscribeToTicker(tickerInfo: TickerInfo) {
        Timber.d("subscribe To Ticker")
        tradeRepublicWebSocket.startTickerSubscription(tickerInfo.mapToTicketSubscription())
    }

    override fun unsubscribeFromTicker(tickerInfo: TickerInfo) {
        Timber.d("unSubscribe From Ticker")
        tradeRepublicWebSocket.stopTickerSubscription(tickerInfo.mapToTicketUnsubscription())
    }

}