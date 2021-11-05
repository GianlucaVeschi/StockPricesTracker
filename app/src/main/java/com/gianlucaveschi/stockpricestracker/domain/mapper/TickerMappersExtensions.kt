package com.gianlucaveschi.stockpricestracker.domain.mapper

import com.gianlucaveschi.stockpricestracker.domain.getHardcodedTickerInfo
import com.gianlucaveschi.stockpricestracker.domain.model.*

fun TickerApiModel.mapToUiModel(): TickerUiModel = this.run {
    TickerUiModel(
        tickerInfo = getHardcodedTickerInfo().first { it.isin == this.isin },
        price = price
    )
}


fun TickerInfo.mapToTicketSubscription(): TickerSubscription = this.run {
    TickerSubscription(this.isin)
}

fun TickerInfo.mapToTicketUnsubscription(): TickerUnsubscription = this.run {
    TickerUnsubscription(this.isin)
}