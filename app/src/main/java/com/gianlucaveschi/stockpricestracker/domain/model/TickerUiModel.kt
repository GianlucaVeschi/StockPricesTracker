package com.gianlucaveschi.stockpricestracker.domain.model

import com.gianlucaveschi.stockpricestracker.util.Utils
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

//@JsonClass(generateAdapter = true)
data class TickerUiModel(
    val tickerInfo: TickerInfo,
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