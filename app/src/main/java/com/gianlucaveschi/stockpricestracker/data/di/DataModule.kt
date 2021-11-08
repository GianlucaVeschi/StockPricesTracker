package com.gianlucaveschi.stockpricestracker.data.di

import android.content.Context
import android.provider.SyncStateContract
import com.gianlucaveschi.stockpricestracker.BuildConfig
import com.gianlucaveschi.stockpricestracker.StockPricesTrackerApplication
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocketImpl
import com.gianlucaveschi.stockpricestracker.data.network.scarlet.TradeRepublicService
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepositoryImpl
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
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

@Module
@InstallIn(SingletonComponent::class)
class WebSocketListenerModule {
    @Provides
    fun provideOpenConnectionRequest(): Request {
        return Request
            .Builder()
            .url(TRADE_REPUBLIC_SOCKET_URL)
            .build()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    @Provides
    fun provideWebSocketListener(
        okHttpClient: OkHttpClient,
        openConnectionRequest: Request
    ) : TradeRepublicWebSocket = TradeRepublicWebSocketImpl(
        okHttpClient,
        openConnectionRequest
    )

    companion object {
        const val TRADE_REPUBLIC_SOCKET_URL = "ws://159.89.15.214:8080/"
    }
}

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

@Module
@InstallIn(SingletonComponent::class)
class ScarletModule {

    @ExperimentalCoroutinesApi
    @Provides
    fun provideScarlet(
        application: StockPricesTrackerApplication,
        client: OkHttpClient,
        moshi: Moshi
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

    companion object {
        const val TRADE_REPUBLIC_SOCKET_URL = "ws://159.89.15.214:8080/"
    }
}