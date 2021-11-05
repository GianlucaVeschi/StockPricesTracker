package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.repo.MainRepository

class StartSubscriptionToStockMarketUseCase(
    private val mainRepository: MainRepository
) {
    fun run(tickerInfo: TickerInfo) {
        mainRepository.subscribeToTicker(tickerInfo)
    }
}