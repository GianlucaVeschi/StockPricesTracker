package com.gianlucaveschi.stockpricestracker.data.mapper

import com.gianlucaveschi.stockpricestracker.domain.entities.getHardcodedTickerInfo
import com.gianlucaveschi.stockpricestracker.domain.entities.*

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