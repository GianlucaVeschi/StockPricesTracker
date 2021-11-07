package com.gianlucaveschi.stockpricestracker.data.network

import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import timber.log.Timber

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
class TradeRepublicWebSocketImpl(
    private val client: OkHttpClient,
    private val openConnectionRequest: Request
) : TradeRepublicWebSocket {

    //Persistent bi-directional communication channel between a client (Android) and a BE service
    private lateinit var webSocket: WebSocket

    private val tickerUpdatesFlow: Flow<TickerApiModel> = callbackFlow {
        val json = Json { ignoreUnknownKeys = true }
        val webSocketListener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Timber.d("onOpen")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Timber.d("onMessage $text")
                trySend(json.decodeFromString(text))
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                t.printStackTrace()
            }
        }

        webSocket = client.newWebSocket(openConnectionRequest, webSocketListener)

        awaitClose { webSocket.close(CLOSE_CONNECTION_TIMEOUT, null) }
    }

    override fun observeTickerUpdates(): Flow<TickerApiModel> = tickerUpdatesFlow

    override fun subscribeToTicker(tickerSubscription: TickerSubscription) {
        webSocket.send(Json.encodeToString(tickerSubscription))
    }

    override fun unsubscribeFromTicker(tickerUnsubscription: TickerUnsubscription) {
        webSocket.send(Json.encodeToString(tickerUnsubscription))
    }

    companion object {
        const val CLOSE_CONNECTION_TIMEOUT = 1000
    }
}