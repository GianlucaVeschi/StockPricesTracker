package com.gianlucaveschi.stockpricestracker.di

import com.gianlucaveschi.stockpricestracker.network.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.repo.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        service : TradeRepublicService
    ) : MainRepository = MainRepositoryImpl(
        service
    )
}