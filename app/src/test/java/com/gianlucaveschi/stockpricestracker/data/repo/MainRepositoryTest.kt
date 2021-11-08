package com.gianlucaveschi.stockpricestracker.data.repo

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerSubscription
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUnsubscription
import com.gianlucaveschi.stockpricestracker.data.network.TradeRepublicWebSocket
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MainRepositoryTest : BaseJunitTest<MainRepository>() {

    private val webSocket: TradeRepublicWebSocket = mockk(relaxed = true)

    override fun initSelf() = MainRepositoryImpl(webSocket)

    @Test
    fun `should verify ticker observation starts `() {
        systemUnderTest.observeTicker()

        verify(exactly = 1) { webSocket.observeTickerUpdates() }
    }

    @Test
    fun `should verify subscription to ticker`() {
        systemUnderTest.subscribeToTicker(appleTickerUiModel)

        verify(exactly = 1) { webSocket.subscribeToTicker(appleTickerSubscription) }
    }

    @Test
    fun `should verify unsubscription from ticker`() {
        systemUnderTest.unsubscribeFromTicker(appleTickerUiModel)

        verify(exactly = 1) { webSocket.unsubscribeFromTicker(appleTickerUnsubscription) }
    }

}