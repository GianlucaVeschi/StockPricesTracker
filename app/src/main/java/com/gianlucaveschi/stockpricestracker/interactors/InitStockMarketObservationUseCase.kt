package com.gianlucaveschi.stockpricestracker.interactors

import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import timber.log.Timber

class InitStockMarketObservationUseCase(
    private val mainRepository: MainRepository
) {
    fun run() {
        Timber.d("init observation")
        mainRepository.initTickerObservations()
    }
}