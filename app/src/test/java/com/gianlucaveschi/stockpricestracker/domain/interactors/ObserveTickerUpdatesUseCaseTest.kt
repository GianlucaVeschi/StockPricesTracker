package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class ObserveTickerUpdatesUseCaseTest : BaseJunitTest<ObserveTickerUpdatesUseCase>() {

    private val mainRepository: MainRepository = mockk(relaxed = true)
    private val tickerUiModel = TickerUiModel(appleTickerUiModel.name, appleTickerUiModel.isin, BigDecimal.ONE)

    override fun initSelf(): ObserveTickerUpdatesUseCase {
        return ObserveTickerUpdatesUseCase(mainRepository)
    }

    @Test
    fun `should return a TickerUiModel`() = runBlocking {

        coEvery { mainRepository.initTickerObservations() } returns flowOf(tickerUiModel)

        val result = tested().first()

        assertEquals(appleTickerUiModel, result)
    }

}