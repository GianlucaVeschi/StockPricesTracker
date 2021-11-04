package com.gianlucaveschi.stockpricestracker.util

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.stockpricestracker.R
import com.gianlucaveschi.stockpricestracker.ui.util.LinearLayoutManager
import com.gianlucaveschi.stockpricestracker.util.Constants.CURRENCY_SYMBOL
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object Utils {

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