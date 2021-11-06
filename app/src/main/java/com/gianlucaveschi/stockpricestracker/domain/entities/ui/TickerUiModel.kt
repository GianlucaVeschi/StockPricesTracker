package com.gianlucaveschi.stockpricestracker.domain.entities.ui

import com.gianlucaveschi.stockpricestracker.domain.entities.util.formatAmountWithCurrency
import java.math.BigDecimal

data class TickerUiModel(
    val name: String,
    val isin: String,
    val price: BigDecimal? = null
) {
    fun getFormattedPrice() = price?.formatAmountWithCurrency() ?: "..."
}