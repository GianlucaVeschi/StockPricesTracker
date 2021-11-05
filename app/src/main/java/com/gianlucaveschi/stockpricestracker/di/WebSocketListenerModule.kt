package com.gianlucaveschi.stockpricestracker.di

import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocketImpl
import com.gianlucaveschi.stockpricestracker.network.wslistener.TradeRepublicWebSocket
import com.gianlucaveschi.stockpricestracker.util.Constants
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
            .url(Constants.TRADE_REPUBLIC_SOCKET_URL)
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
}