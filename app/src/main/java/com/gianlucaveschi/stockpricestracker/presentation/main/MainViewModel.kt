package com.gianlucaveschi.stockpricestracker.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.stockpricestracker.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeTickerUpdatesUseCase: ObserveTickerUpdatesUseCase,
    private val subscribeToTickerUseCase: SubscribeToTickerUseCase,
    private val unsubscribeFromTickerUseCase: UnsubscribeFromTickerUseCase
) : ViewModel() {

    //todo : Change
    private val _newDataAvailable = SingleLiveEvent<Unit>()
    val newDataAvailable: LiveData<Unit> = _newDataAvailable

    private val _tickersListStateFlow = MutableStateFlow(mutableListOf<TickerUiModel>().apply {
        addAll(getHardcodedTickerUiModel())
    })
    val tickersListStateFlow: StateFlow<List<TickerUiModel>> = _tickersListStateFlow

    init {
        observeTickersUpdates()
        subscribeToAllTickers()
    }

    fun observeTickersUpdates() {
        observeTickerUpdatesUseCase().onEach { tickerUiModel ->
            Timber.d("Collecting UiModel $tickerUiModel")
            updateTickerInTheList(tickerUiModel)
        }.launchIn(viewModelScope)
    }

    private fun updateTickerInTheList(ticker: TickerUiModel) {
        val index = _tickersListStateFlow.value.getTickerIndex(ticker)
        _tickersListStateFlow.value[index] = ticker

        _newDataAvailable.value = Unit
    }

    fun subscribeToAllTickers() {
        _tickersListStateFlow.value.forEach {
            subscribeToTickerUseCase(it)
        }
    }

    fun unsubscribeFromAllTickers() {
        _tickersListStateFlow.value.forEach {
            unsubscribeFromTickerUseCase(it)
        }
    }
}

private fun MutableList<TickerUiModel>.getTickerIndex(ticker: TickerUiModel): Int {
    return this.indexOfFirst { it.isin == ticker.isin }
}
