package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerUiModel
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import kotlinx.coroutines.flow.Flow

class ObserveTickerUpdatesUseCase(
    private val mainRepository: MainRepository
) {
    operator fun invoke() : Flow<TickerUiModel> = mainRepository.initTickerObservations()
}