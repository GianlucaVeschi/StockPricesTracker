package com.gianlucaveschi.stockpricestracker.data.repo

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.Shared.appleTickerSubscription
import com.gianlucaveschi.stockpricestracker.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.Shared.appleTickerUnsubscription
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MainRepositoryImplTest : BaseJunitTest<MainRepository>() {

    private val webSocket: TradeRepublicWebSocket = mockk(relaxed = true)

    override fun initSelf() = MainRepositoryImpl(webSocket)

    @Test
    fun `should verify ticker observation starts `() {
        tested.observeTicker()

        verify(exactly = 1) { webSocket.observeTickerUpdates() }
    }

    @Test
    fun `should verify subscription to ticker`() {
        tested.subscribeToTicker(appleTickerUiModel)

        verify(exactly = 1) { webSocket.subscribeToTicker(appleTickerSubscription) }
    }

    @Test
    fun `should verify unsubscription from ticker`() {
        tested.unsubscribeFromTicker(appleTickerUiModel)

        verify(exactly = 1) { webSocket.unsubscribeFromTicker(appleTickerUnsubscription) }
    }

}