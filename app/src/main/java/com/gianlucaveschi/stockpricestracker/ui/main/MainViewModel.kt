package com.gianlucaveschi.stockpricestracker.ui.main

import androidx.lifecycle.ViewModel
import com.gianlucaveschi.stockpricestracker.domain.model.StockPriceModel
import com.gianlucaveschi.stockpricestracker.domain.getHardcodedStockPriceModels
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val _stockModelData = mutableListOf<StockPriceModel>().apply { addAll(getHardcodedStockPriceModels()) }
    val stockModelData: List<StockPriceModel> = _stockModelData

    init {
        Timber.d("init")
    }

    fun subscribeToStocks() {
        Timber.d("viewModel.subscribeToStocks()")
    }

    fun unsubscribeFromStocks() {
        Timber.d("viewModel.unsubscribeToStocks()")
    }
}