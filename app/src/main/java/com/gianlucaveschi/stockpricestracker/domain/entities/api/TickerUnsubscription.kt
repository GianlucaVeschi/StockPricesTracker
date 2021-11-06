package com.gianlucaveschi.stockpricestracker.domain.entities.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerUnsubscription(
  @SerialName("unsubscribe") val isin: String
)