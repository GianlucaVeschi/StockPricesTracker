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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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
        //subscribeToAllTickers()
    }

    fun observeTickersUpdates() {
        viewModelScope.launch {
            observeTickerUpdatesUseCase().collect { tickerUiModel ->
                Timber.d("Collecting UiModel $tickerUiModel")
                updateUI(tickerUiModel)
            }
        }
    }

    //todo : Change
    private fun updateUI(tickerUiModel: TickerUiModel) {
        _tickersListStateFlow.value.apply {
            set(indexOfFirst {
                it.isin == tickerUiModel.isin
            }, tickerUiModel)
        }

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