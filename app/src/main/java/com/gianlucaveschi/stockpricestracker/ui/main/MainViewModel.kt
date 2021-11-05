package com.gianlucaveschi.stockpricestracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestracker.domain.model.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.getTickersList
import com.gianlucaveschi.stockpricestracker.interactors.InitStockMarketObservationUseCase
import com.gianlucaveschi.stockpricestracker.interactors.StartSubscriptionToStockMarketUseCase
import com.gianlucaveschi.stockpricestracker.interactors.StopSubscriptionToStockMarketUseCase
import com.gianlucaveschi.stockpricestracker.ui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initStockMarketObservationUseCase: InitStockMarketObservationUseCase,
    private val startSubscriptionToStockMarketUseCase: StartSubscriptionToStockMarketUseCase,
    private val stopSubscriptionToStockMarketUseCase: StopSubscriptionToStockMarketUseCase
) : ViewModel() {

    private val _newDataAvailable = SingleLiveEvent<Unit>()
    val newDataAvailable: LiveData<Unit> = _newDataAvailable

    private val _tickersList = mutableListOf<TickerUiModel>().apply {
        addAll(getTickersList())
    }
    val tickersList: List<TickerUiModel> = _tickersList

    init {
        Timber.d("init observation")
        viewModelScope.launch {
            initStockMarketObservationUseCase.run().collect { tickerUiModel ->
                Timber.d("Collecting UiModel $tickerUiModel")
                _tickersList.apply {
                    set(indexOfFirst { it.tickerInfo == tickerUiModel.tickerInfo }, tickerUiModel)
                }
                _newDataAvailable.value = Unit
            }
        }
        subscribeToStocks()
    }

    fun subscribeToStocks() {
        Timber.d("start observation")
        _tickersList.forEach { startSubscriptionToStockMarketUseCase.run(it.tickerInfo) }
    }

    fun unsubscribeFromStocks() {
        Timber.d("stop observation")
        tickersList.forEach { stopSubscriptionToStockMarketUseCase.run(it.tickerInfo) }
    }
}