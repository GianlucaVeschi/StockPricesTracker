package com.gianlucaveschi.stockpricestracker.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScarletTickerSubscription(
    @Json(name = "subscribe") val isin: String
)