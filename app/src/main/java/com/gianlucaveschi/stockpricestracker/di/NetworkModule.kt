package com.gianlucaveschi.stockpricestracker.di

import android.content.Context
import com.gianlucaveschi.stockpricestracker.BuildConfig
import com.gianlucaveschi.stockpricestracker.StockPricesTrackerApplication
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesApplication(
        @ApplicationContext context: Context
    ): StockPricesTrackerApplication {
        return context as StockPricesTrackerApplication
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
            else HttpLoggingInterceptor.Level.NONE
        )
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }
}