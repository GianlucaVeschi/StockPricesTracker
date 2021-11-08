package com.gianlucaveschi.stockpricestracker.data.mapper

import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.scarlet.ScarletTickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel

fun TickerApiModel.mapToUiModel(): TickerUiModel = this.run {
    TickerUiModel(
        name = getHardcodedTickerUiModel().first { it.isin == this.isin }.name,
        isin = isin,
        price = price
    )
}

fun TickerUiModel.mapToTicketSubscription(): TickerSubscription = this.run {
    TickerSubscription(this.isin)
}

fun TickerUiModel.mapToTicketUnsubscription(): TickerUnsubscription = this.run {
    TickerUnsubscription(this.isin)
}

fun ScarletTickerApiModel.mapToUiModel(): TickerUiModel = this.run {
    TickerUiModel(
        name = getHardcodedTickerUiModel().first { it.isin == this.isin }.name,
        isin = isin,
        price = price
    )
}