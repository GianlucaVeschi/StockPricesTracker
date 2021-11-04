package com.gianlucaveschi.stockpricestracker.di

import android.content.Context
import com.gianlucaveschi.stockpricestracker.BuildConfig
import com.gianlucaveschi.stockpricestracker.StockPricesTrackerApplication
import com.gianlucaveschi.stockpricestracker.StockPricesTrackerApplication_GeneratedInjector
import com.gianlucaveschi.stockpricestracker.network.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.network.util.FlowStreamAdapter
import com.gianlucaveschi.stockpricestracker.util.Constants.TRADE_REPUBLIC_SOCKET_URL
import com.squareup.moshi.Moshi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
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

    @Provides
    fun provideScarlet(
        application: StockPricesTrackerApplication,
        client: OkHttpClient,
        moshi : Moshi
    ): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(client.newWebSocketFactory(TRADE_REPUBLIC_SOCKET_URL))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory(moshi))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }

    @Provides
    fun provideTradeRepublicService(
        scarlet: Scarlet
    ): TradeRepublicService {
        return scarlet.create()
    }
}