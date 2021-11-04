package com.gianlucaveschi.stockpricestracker.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object Utils {

    //ENDPOINT URL
    const val TRADE_REPUBLIC_SOCKET_URL = "ws://159.89.15.214:8080/"

    //TIMEOUTS
    const val CONNECTION_TIMEOUT = 10 // 10 seconds
    const val READ_TIMEOUT = 2 // 2 seconds
    const val WRITE_TIMEOUT = 2 // 2 seconds

    private const val CURRENCY_SYMBOL = "€"

    /**
     * format an amount to 2 fraction digits and appends a currency symbol
     */
    fun formatAmountWithCurrency(amount: BigDecimal): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        (format as? DecimalFormat)?.apply {

            val dfs: DecimalFormatSymbols = DecimalFormat().decimalFormatSymbols
            dfs.currencySymbol = CURRENCY_SYMBOL
            decimalFormatSymbols = dfs

            maximumFractionDigits = if (amount.compareTo(BigDecimal.ZERO) == 0) 0 else 2
            minimumFractionDigits = if (amount.compareTo(BigDecimal.ZERO) == 0) 0 else 2
        }

        return format.format(amount)
    }
}