package com.gianlucaveschi.stockpricestracker.domain.entities.scarlet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScarletTickerUnsubscription(
  @SerialName("unsubscribe") val isin: String
)