package com.gianlucaveschi.stockpricestracker.testutils

import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel


object Shared {
    private const val tickerName = "Apple Inc."
    private const val tickerIsin = "US0378331005"
    private const val tickerPrice: Double = 1.00
    private const val tickerBid: Double = 2.00
    private const val tickerAsk: Double = 3.00


    val appleTickerApiModel = TickerApiModel(tickerIsin, tickerPrice, tickerBid, tickerAsk)
    val appleTickerUiModel = TickerUiModel(tickerName, tickerIsin, tickerPrice)
    val appleTickerSubscription = TickerSubscription(tickerIsin)
    val appleTickerUnsubscription = TickerUnsubscription(tickerIsin)
}