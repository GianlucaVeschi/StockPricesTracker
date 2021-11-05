package com.gianlucaveschi.stockpricestracker.domain.model.scarlet


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScarletTickerApiModel(
    @Json(name = "isin") val isin: String,
    @Json(name = "price") val price: Float,
)
