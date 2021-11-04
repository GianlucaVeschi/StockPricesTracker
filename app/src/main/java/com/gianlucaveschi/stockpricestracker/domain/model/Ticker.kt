package com.gianlucaveschi.stockpricestracker.domain.model

import com.gianlucaveschi.stockpricestracker.util.Utils
import java.math.BigDecimal

data class Ticker(
    val stock: TickerInfo,
    val price: BigDecimal? = null
) {
    fun getFormattedPrice(): String {
        return if (price == null) "..."
        else Utils.formatAmountWithCurrency(price)
    }
}

data class TickerInfo(
    val name: String,
    val isin: String
)