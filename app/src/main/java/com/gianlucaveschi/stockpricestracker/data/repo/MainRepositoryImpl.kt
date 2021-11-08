package com.gianlucaveschi.stockpricestracker.data.repo

import com.gianlucaveschi.stockpricestracker.data.mapper.mapToTicketSubscription
import com.gianlucaveschi.stockpricestracker.data.mapper.mapToTicketUnsubscription
import com.gianlucaveschi.stockpricestracker.data.mapper.mapToUiModel
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRepositoryImpl(
    private val tradeRepublicWebSocket: TradeRepublicWebSocket
) : MainRepository {

    /** Observe Last updated ticker and convert it to Ui Model */
    override fun observeTicker(): Flow<TickerUiModel> {
        return tradeRepublicWebSocket.observeTickerUpdates().map { it.mapToUiModel() }
    }

    override fun subscribeToTicker(tickerUiModel: TickerUiModel) {
        tradeRepublicWebSocket.subscribeToTicker(tickerUiModel.mapToTicketSubscription())
    }

    override fun unsubscribeFromTicker(tickerUiModel: TickerUiModel) {
        tradeRepublicWebSocket.unsubscribeFromTicker(tickerUiModel.mapToTicketUnsubscription())
    }

}