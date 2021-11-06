package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerInfo
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository

class SubscribeToTickerUseCase(
    private val mainRepository: MainRepository
) {
    fun run(tickerInfo: TickerInfo) {
        mainRepository.subscribeToTicker(tickerInfo)
    }
}