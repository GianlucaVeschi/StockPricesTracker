package com.gianlucaveschi.stockpricestracker.domain.interactors.di

import com.gianlucaveschi.stockpricestracker.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
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
        mainRepository: MainRepository
    ) = ObserveTickerUpdatesUseCase(
        mainRepository
    )

    @ViewModelScoped
    @Provides
    fun provideStartSubscriptionToStockMarketUseCase(
        mainRepository: MainRepository
    ) = SubscribeToTickerUseCase(
        mainRepository
    )

    @ViewModelScoped
    @Provides
    fun provideStopSubscriptionToStockMarketUseCase(
        mainRepository: MainRepository
    ) = UnsubscribeFromTickerUseCase(
        mainRepository
    )

}