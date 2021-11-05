package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import timber.log.Timber

class StopSubscriptionToStockMarketUseCase(
    private val mainRepository: MainRepository
) {
    fun run() {
        Timber.d("stop observation")
        val tickerInfo = TickerInfo("Apple","US0378331005")
        mainRepository.unsubscribeFromTicker(tickerInfo)
    }
}