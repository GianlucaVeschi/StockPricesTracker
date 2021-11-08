package com.gianlucaveschi.stockpricestracker.domain.entities.scarlet

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class ScarletTickerUnsubscription(
  @Json(name = "unsubscribe") val isin: String
)