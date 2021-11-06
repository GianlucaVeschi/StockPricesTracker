package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerUiModel
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import kotlinx.coroutines.flow.Flow

class InitStockMarketObservationUseCase(
    private val mainRepository: MainRepository
) {
    fun run() : Flow<TickerUiModel> = mainRepository.initTickerObservations()
}