package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import timber.log.Timber

class StopSubscriptionToStockMarketUseCase(
    private val mainRepository: MainRepository
) {
    fun run() {
        Timber.d("stop observation")
        mainRepository.unsubscribeFromTicker(TickerInfo("Apple","1234"))
    }
}