package com.gianlucaveschi.stockpricestracker.di

import com.gianlucaveschi.stockpricestracker.network.scarlet.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocketImpl
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.repo.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideMainRepository(
        service : TradeRepublicService,
        tradeRepublicWebSocketImpl : TradeRepublicWebSocketImpl
    ) : MainRepository = MainRepositoryImpl(
        service,
        tradeRepublicWebSocketImpl
    )
}