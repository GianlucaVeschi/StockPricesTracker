package com.gianlucaveschi.stockpricestracker.domain.entities.scarlet

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName

@JsonClass(generateAdapter = true)
data class ScarletTickerApiModel(
    @Json(name = "isin") val isin: String,
    @Json(name = "price") val price: Double,
    @SerialName("bid") val bid: Double,
    @SerialName("ask") val ask: Double
)
