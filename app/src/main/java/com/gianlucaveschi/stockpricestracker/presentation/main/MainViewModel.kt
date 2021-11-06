package com.gianlucaveschi.stockpricestracker.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestracker.domain.entities.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.getTickersList
import com.gianlucaveschi.stockpricestracker.domain.interactors.InitStockMarketObservationUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.stockpricestracker.presentation.util.SingleLiveEvent
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
    private val subscribeToTickerUseCase: SubscribeToTickerUseCase,
    private val unsubscribeFromTickerUseCase: UnsubscribeFromTickerUseCase
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
        subscribeToTicker()
    }

    fun subscribeToTicker() {
        _tickersListStateFlow.value.forEach {
            subscribeToTickerUseCase.run(it.tickerInfo)
        }
    }

    fun unsubscribeFromTicker() {
        _tickersListStateFlow.value.forEach {
            unsubscribeFromTickerUseCase.run(it.tickerInfo)
        }
    }
}