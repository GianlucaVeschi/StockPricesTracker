package com.gianlucaveschi.stockpricestracker.di

import com.gianlucaveschi.stockpricestracker.interactors.InitStockMarketObservationUseCase
import com.gianlucaveschi.stockpricestracker.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.interactors.UnsubscribeFromTickerUseCase
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
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
    ) = InitStockMarketObservationUseCase(
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