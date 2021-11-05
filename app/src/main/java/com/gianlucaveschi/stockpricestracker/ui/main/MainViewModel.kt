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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _tickersListStateFlow = MutableStateFlow(mutableListOf<TickerUiModel>().apply {
        addAll(getTickersList())
    })
    val tickersListStateFlow: StateFlow<List<TickerUiModel>> = _tickersListStateFlow

    init {
        Timber.d("init observation")
        viewModelScope.launch {
            initStockMarketObservationUseCase.run().collect { tickerUiModel ->
                Timber.d("Collecting UiModel $tickerUiModel")

                _tickersListStateFlow.value.apply {
                    set(indexOfFirst { it.tickerInfo == tickerUiModel.tickerInfo }, tickerUiModel)
                }

                _newDataAvailable.value = Unit
            }
        }
        subscribeToStocks()
    }

    fun subscribeToStocks() {
        _tickersListStateFlow.value.forEach {
            startSubscriptionToStockMarketUseCase.run(it.tickerInfo)
        }
    }

    fun unsubscribeFromStocks() {
        _tickersListStateFlow.value.forEach {
            stopSubscriptionToStockMarketUseCase.run(it.tickerInfo)
        }
    }
}