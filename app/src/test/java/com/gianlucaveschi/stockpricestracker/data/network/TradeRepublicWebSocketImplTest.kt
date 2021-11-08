package com.gianlucaveschi.stockpricestracker.data.network

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerApiModel
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerSubscription
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUnsubscription
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import org.junit.Ignore
import org.junit.Test

class TradeRepublicWebSocketTest : BaseJunitTest<TradeRepublicWebSocket>() {

    private val client: OkHttpClient = mockk()
    private val openConnectionRequest: Request = mockk()
    private val jsonEncoder: Json = mockk()
    private val webSocket: WebSocket = mockk()

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun initSelf() = TradeRepublicWebSocketImpl(client, openConnectionRequest, jsonEncoder)


    @Test
    fun `should start tickers observation`() {
        systemUnderTest.observeTickerUpdates()
    }


    @ExperimentalSerializationApi
    @Test
    @Ignore("Fix me - websocket has not been initialized")
    fun `should subscribe to ticker`() {
        systemUnderTest.subscribeToTicker(appleTickerSubscription)

        verify(exactly = 1) { webSocket.send(Json.encodeToString(appleTickerSubscription)) }
    }

    @ExperimentalSerializationApi
    @Test
    @Ignore("Fix me - websocket has not been initialized")
    fun `should unsubscribe from ticker`() {
        systemUnderTest.unsubscribeFromTicker(appleTickerUnsubscription)

        verify(exactly = 1) { webSocket.send(Json.encodeToString(appleTickerUnsubscription)) }
    }

}