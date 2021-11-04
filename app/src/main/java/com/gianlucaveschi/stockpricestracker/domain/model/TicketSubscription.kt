package com.gianlucaveschi.stockpricestracker.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketSubscription(
  @SerialName("subscribe") val isin: String
)