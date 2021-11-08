package com.gianlucaveschi.stockpricestracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.UnsubscribeFromTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeTickerUpdatesUseCase: ObserveTickerUpdatesUseCase,
    private val subscribeToTickerUseCase: SubscribeToTickerUseCase,
    private val unsubscribeFromTickerUseCase: UnsubscribeFromTickerUseCase
) : ViewModel() {

    private val tickersList: MutableList<TickerUiModel> = getHardcodedTickerUiModel()

    private val _tickerStateFlow = MutableStateFlow(TickerUiModel("Apple Inc.", "US0378331005"))
    val tickerStateFlow: StateFlow<TickerUiModel> = _tickerStateFlow

    init {
        observeTickersUpdates()
        subscribeToAllTickers()
    }

    fun observeTickersUpdates() {
        observeTickerUpdatesUseCase().onEach { ticker ->
            _tickerStateFlow.value = ticker
        }.catch { error ->
            Timber.d("Collecting failed with ${error.message}")
        }.launchIn(viewModelScope)
    }

    fun subscribeToAllTickers() {
        tickersList.forEach {
            subscribeToTickerUseCase(it)
        }
    }

    fun unsubscribeFromAllTickers() {
        tickersList.forEach {
            unsubscribeFromTickerUseCase(it)
        }
    }
}
