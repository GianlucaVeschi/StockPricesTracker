package com.gianlucaveschi.stockpricestracker.data.repo

import com.gianlucaveschi.stockpricestracker.data.mapper.mapToTicketSubscription
import com.gianlucaveschi.stockpricestracker.data.mapper.mapToTicketUnsubscription
import com.gianlucaveschi.stockpricestracker.data.mapper.mapToUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.*
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@ExperimentalSerializationApi
class MainRepositoryImpl(
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