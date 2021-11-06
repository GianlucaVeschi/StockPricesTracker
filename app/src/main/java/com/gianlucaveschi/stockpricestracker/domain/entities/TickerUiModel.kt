package com.gianlucaveschi.stockpricestracker.domain.entities

import com.gianlucaveschi.stockpricestracker.domain.entities.util.formatAmountWithCurrency
import java.math.BigDecimal

data class TickerUiModel(
    val tickerInfo: TickerInfo,
    val price: BigDecimal? = null
) {
    fun getFormattedPrice() = price?.formatAmountWithCurrency() ?: "..."
}

data class TickerInfo(
    val name: String,
    val isin: String
)