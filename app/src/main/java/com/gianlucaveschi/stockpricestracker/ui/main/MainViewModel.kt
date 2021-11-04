package com.gianlucaveschi.stockpricestracker.ui.main

import androidx.lifecycle.ViewModel
import com.gianlucaveschi.stockpricestracker.domain.StockModel
import com.gianlucaveschi.stockpricestracker.domain.StockPriceModel
import timber.log.Timber

class MainViewModel : ViewModel() {

    init {
        Timber.d("init")
    }

    val stockModelsList: List<StockPriceModel> = listOf(
        StockPriceModel(StockModel("Microsoft Corp.", "US5949181045"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Invesco Ltd.", "BMG491BT1088"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Apple Inc.", "US0378331005"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Tesla Motors Inc.", "US88160R1014"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Tiffany & Co.", "US8865471085"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Amazon.com Inc.", "US0231351067"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Nike, Inc.", "US6541061031"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Kellogg Co.", "US4878361082"), 0.toBigDecimal()),
        StockPriceModel(StockModel("JPMorgan & Co.", "US46625H1005"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Microsoft Corp.", "US5949181045"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Invesco Ltd.", "BMG491BT1088"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Apple Inc.", "US0378331005"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Tesla Motors Inc.", "US88160R1014"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Tiffany & Co.", "US8865471085"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Amazon.com Inc.", "US0231351067"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Nike, Inc.", "US6541061031"), 0.toBigDecimal()),
        StockPriceModel(StockModel("Kellogg Co.", "US4878361082"), 0.toBigDecimal()),
        StockPriceModel(StockModel("JPMorgan & Co.", "US46625H1005"), 0.toBigDecimal())
    )

    fun subscribeToStocks() {
        Timber.d("viewModel.subscribeToStocks()")
    }

    fun unsubscribeFromStocks() {
        Timber.d("viewModel.unsubscribeToStocks()")
    }
}