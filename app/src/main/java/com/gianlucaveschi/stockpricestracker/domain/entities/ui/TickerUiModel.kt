package com.gianlucaveschi.stockpricestracker.domain.entities.ui

import com.gianlucaveschi.stockpricestracker.domain.entities.util.shorten

data class TickerUiModel(
    val name: String,
    val isin: String,
    val price: Double? = null
) {
    fun getFormattedPrice() : String = price?.shorten(2) ?: "0.00"
}