package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.domain.model.TickerUiModel
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import kotlinx.coroutines.flow.Flow

class InitStockMarketObservationUseCase(
    private val mainRepository: MainRepository
) {
    fun run() : Flow<TickerUiModel> = mainRepository.initTickerObservations()
}