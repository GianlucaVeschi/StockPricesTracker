package com.gianlucaveschi.stockpricestracker.di

import com.gianlucaveschi.stockpricestracker.interactors.InitStockMarketObservationUseCase
import com.gianlucaveschi.stockpricestracker.interactors.StartSubscriptionToStockMarketUseCase
import com.gianlucaveschi.stockpricestracker.interactors.StopSubscriptionToStockMarketUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideInitStockMarketObservationUseCase(
    ): InitStockMarketObservationUseCase {
        return InitStockMarketObservationUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideStartSubscriptionToStockMarketUseCase(
    ): StartSubscriptionToStockMarketUseCase {
        return StartSubscriptionToStockMarketUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideStopSubscriptionToStockMarketUseCase(
    ): StopSubscriptionToStockMarketUseCase {
        return StopSubscriptionToStockMarketUseCase()
    }

}