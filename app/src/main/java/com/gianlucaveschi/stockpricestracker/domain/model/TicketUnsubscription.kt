package com.gianlucaveschi.stockpricestracker.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketUnsubscription(
  @SerialName("unsubscribe") val isin: String
)