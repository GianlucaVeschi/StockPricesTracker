package com.gianlucaveschi.stockpricestracker.network.wslistener

import android.telecom.ConnectionRequest
import com.gianlucaveschi.stockpricestracker.domain.getTickersList
import com.gianlucaveschi.stockpricestracker.util.Constants.TRADE_REPUBLIC_SOCKET_URL
import okhttp3.*
import okio.ByteString
import timber.log.Timber

class TradeRepWebSocketListener(
    private val client: OkHttpClient,
    private val openConnectionRequest: Request
) {

    //Persistent bi-directional communication channel between a client (Android) and a BE service
    private lateinit var webSocket: WebSocket

    private var isConnected = false
    private val tickerList = getTickersList()

    fun launchWebSocketConnection() {
        webSocket = client.newWebSocket(openConnectionRequest, webSocketListener);
    }

    //Internal WebSocketListener
    private val webSocketListener: WebSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {

            Timber.d("onOpen")
            isConnected = true

            for (i in tickerList.indices) {
                val currentISIN: String = tickerList[i].tickerInfo.isin
                val toSend = "{\"subscribe\":\"<$currentISIN>\"}"
                Timber.d("current ISIN : $toSend")
                webSocket.send(toSend)
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Timber.d("MESSAGE: $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            Timber.d("MESSAGE: ${bytes.hex()}")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            isConnected = false
            webSocket.close(1000, null)
            webSocket.cancel()
            Timber.d("CLOSE: $code $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Timber.d("onClosed: $code REASON: $reason")
        }
    }
}