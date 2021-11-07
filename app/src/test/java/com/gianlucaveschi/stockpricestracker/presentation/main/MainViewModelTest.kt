package com.gianlucaveschi.stockpricestracker.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.MainCoroutineRule
import com.gianlucaveschi.stockpricestracker.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.util.getHardcodedTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.interactors.ObserveTickerUpdatesUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.SubscribeToTickerUseCase
import com.gianlucaveschi.stockpricestracker.domain.interactors.UnsubscribeFromTickerUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Ignore
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
        tested.observeTickersUpdates()

        getHardcodedTickerUiModel().forEach {
            coVerify(exactly = 1) {
                subscribeToTickerUseCase(it)
            }
        }
    }

    @Test
    fun `should unsubscribe from all tickers`() {
        tested.unsubscribeFromAllTickers()

        getHardcodedTickerUiModel().forEach {
            coVerify(exactly = 1) {
                unsubscribeFromTickerUseCase(it)
            }
        }
    }

}