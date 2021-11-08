package com.gianlucaveschi.stockpricestracker.presentation.main

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.testutils.MainCoroutineRule
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.UnsubscribeFromTickerUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class MainViewModelTest : BaseJunitTest<MainViewModel>() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val observeTickerUpdatesUseCase: ObserveTickerUpdatesUseCase = mockk(relaxed = true)
    private val subscribeToTickerUseCase: SubscribeToTickerUseCase = mockk(relaxed = true)
    private val unsubscribeFromTickerUseCase: UnsubscribeFromTickerUseCase = mockk(relaxed = true)

    override fun initSelf() = MainViewModel(
        observeTickerUpdatesUseCase,
        subscribeToTickerUseCase,
        unsubscribeFromTickerUseCase
    )

    @Test
    fun `should subscribe to all Ticker`() {
        systemUnderTest.observeTickersUpdates()

        getHardcodedTickerUiModel().forEach {
            coVerify(exactly = 1) {
                subscribeToTickerUseCase(it)
            }
        }
    }

    @Test
    fun `should unsubscribe from all tickers`() {
        systemUnderTest.unsubscribeFromAllTickers()

        getHardcodedTickerUiModel().forEach {
            coVerify(exactly = 1) {
                unsubscribeFromTickerUseCase(it)
            }
        }
    }

}