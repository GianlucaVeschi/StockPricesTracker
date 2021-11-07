package com.gianlucaveschi.stockpricestracker

import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel


object Shared {
    private const val tickerName = "Apple Inc."
    private const val tickerIsin = "US0378331005"
    private const val tickerPrice: Double = 1.00

    val appleTickerUiModel = TickerUiModel(tickerName, tickerIsin, tickerPrice)
    val appleTickerSubscription = TickerSubscription(tickerIsin)
    val appleTickerUnsubscription = TickerUnsubscription(tickerIsin)
}