package com.gianlucaveschi.stockpricestracker.di

import com.gianlucaveschi.stockpricestracker.StockPricesTrackerApplication
import com.gianlucaveschi.stockpricestracker.network.scarlet.util.FlowStreamAdapter
import com.gianlucaveschi.stockpricestracker.util.Constants
import com.squareup.moshi.Moshi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class ScarletModule {

    @Provides
    fun provideScarlet(
        application: StockPricesTrackerApplication,
        client: OkHttpClient,
        moshi: Moshi
    ): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(client.newWebSocketFactory(Constants.TRADE_REPUBLIC_SOCKET_URL))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory(moshi))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }
}