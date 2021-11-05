package com.gianlucaveschi.stockpricestracker.domain.model.scarlet

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScarletTickerUnsubscription(
    @Json(name = "unsubscribe") val isin: String
)