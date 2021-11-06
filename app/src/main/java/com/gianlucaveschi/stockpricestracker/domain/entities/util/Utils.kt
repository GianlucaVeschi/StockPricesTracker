package com.gianlucaveschi.stockpricestracker.domain.entities.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

fun BigDecimal.formatAmountWithCurrency() : String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    (format as? DecimalFormat)?.apply {

        val dfs: DecimalFormatSymbols = DecimalFormat().decimalFormatSymbols
        dfs.currencySymbol = "€"
        decimalFormatSymbols = dfs

        maximumFractionDigits = if (this@formatAmountWithCurrency.compareTo(BigDecimal.ZERO) == 0) 0 else 2
        minimumFractionDigits = if (this@formatAmountWithCurrency.compareTo(BigDecimal.ZERO) == 0) 0 else 2
    }
    return format.format(this@formatAmountWithCurrency)
}