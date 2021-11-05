package com.gianlucaveschi.stockpricestracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gianlucaveschi.stockpricestracker.domain.getHardcodedTickerInfo
import com.gianlucaveschi.stockpricestracker.domain.model.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.getTickersList
import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.stockpricestracker.ui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val subscribeToTickerUseCase: SubscribeToTickerUseCase,
    private val unsubscribeFromTickerUseCase: UnsubscribeFromTickerUseCase
) : ViewModel() {

    private val _newDataAvailable = SingleLiveEvent<Unit>()
    val newDataAvailable: LiveData<Unit> = _newDataAvailable

    private val _tickersList = mutableListOf<TickerUiModel>().apply {
        addAll(getTickersList())
    }
    val tickersList: List<TickerUiModel> = _tickersList

    init {
        Timber.d("init observation")
        subscribeToStocks()
    }

    fun subscribeToStocks() {
        Timber.d("start observation")
        getHardcodedTickerInfo().forEach { ticker ->
            subscribeToTickerUseCase.run(ticker)
        }
    }

    fun unsubscribeFromStocks() {
        Timber.d("stop observation")
        val ticker = TickerInfo("Apple Inc.", "US0378331005")
        unsubscribeFromTickerUseCase.run(ticker)
        //tickersList.forEach { stopSubscriptionToStockMarketUseCase.run(it.tickerInfo) }
    }
}