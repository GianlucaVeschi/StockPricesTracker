package com.gianlucaveschi.stockpricestracker.data.di

import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocketImpl
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.Request

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