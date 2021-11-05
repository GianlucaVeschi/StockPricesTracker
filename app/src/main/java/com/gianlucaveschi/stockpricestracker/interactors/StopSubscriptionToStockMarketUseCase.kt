package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.repo.MainRepository

class StopSubscriptionToStockMarketUseCase(
    private val mainRepository: MainRepository
) {
    fun run(tickerInfo: TickerInfo) {
        mainRepository.unsubscribeFromTicker(tickerInfo)
    }
}