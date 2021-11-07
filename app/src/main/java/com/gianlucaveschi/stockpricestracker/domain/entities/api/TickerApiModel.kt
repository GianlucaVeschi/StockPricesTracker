package com.gianlucaveschi.stockpricestracker.domain.entities.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerApiModel(
    @SerialName("isin") val isin: String,
    @SerialName("price") val price: Double,
    @SerialName("bid") val bid: Double,
    @SerialName("ask") val ask: Double
)
