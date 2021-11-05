package com.gianlucaveschi.stockpricestracker.ui.main

import androidx.lifecycle.ViewModel
import com.gianlucaveschi.stockpricestracker.domain.model.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.getTickersList
import com.gianlucaveschi.stockpricestracker.interactors.InitStockMarketObservationUseCase
import com.gianlucaveschi.stockpricestracker.interactors.StartSubscriptionToStockMarketUseCase
import com.gianlucaveschi.stockpricestracker.interactors.StopSubscriptionToStockMarketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initStockMarketObservationUseCase: InitStockMarketObservationUseCase,
    private val startSubscriptionToStockMarketUseCase: StartSubscriptionToStockMarketUseCase,
    private val stopSubscriptionToStockMarketUseCase: StopSubscriptionToStockMarketUseCase
) : ViewModel() {

    private val _stockPricesList = mutableListOf<TickerUiModel>().apply {
        addAll(getTickersList())
    }
    val stockPricesList: List<TickerUiModel> = _stockPricesList

    init {
        Timber.d("init observation")
        initStockMarketObservationUseCase.run()
    }

    fun subscribeToStocks() {
        Timber.d("start observation")
        startSubscriptionToStockMarketUseCase.run()
    }

    fun unsubscribeFromStocks() {
        Timber.d("stop observation")
        stopSubscriptionToStockMarketUseCase.run()
    }
}