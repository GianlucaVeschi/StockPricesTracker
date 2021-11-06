package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel

class UnsubscribeFromTickerUseCase(
    private val mainRepository: MainRepository
) {
    operator fun invoke(tickerUiModel: TickerUiModel) {
        mainRepository.unsubscribeFromTicker(tickerUiModel)
    }
}