package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerInfo
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository

class UnsubscribeFromTickerUseCase(
    private val mainRepository: MainRepository
) {
    operator fun invoke(tickerInfo: TickerInfo) {
        mainRepository.unsubscribeFromTicker(tickerInfo)
    }
}