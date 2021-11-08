package com.gianlucaveschi.stockpricestracker.domain.entities.scarlet


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScarletTickerSubscription(
  @SerialName("subscribe") val isin: String
)