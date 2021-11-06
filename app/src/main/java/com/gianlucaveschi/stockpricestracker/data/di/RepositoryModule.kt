package com.gianlucaveschi.stockpricestracker.data.di

import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepositoryImpl
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
        tradeRepublicWebSocket : TradeRepublicWebSocket
    ) : MainRepository = MainRepositoryImpl(
        tradeRepublicWebSocket
    )
}