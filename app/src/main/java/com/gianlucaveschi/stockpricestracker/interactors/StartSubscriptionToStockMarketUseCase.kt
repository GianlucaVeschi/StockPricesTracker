package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import timber.log.Timber

class StartSubscriptionToStockMarketUseCase(
    private val mainRepository: MainRepository
) {
    fun run() {
        Timber.d("start observation")
        val tickerInfo = TickerInfo("Apple","US0378331005")
        mainRepository.subscribeToTicker(tickerInfo)
    }
}