package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class ObserveTickerUpdatesUseCaseTest : BaseJunitTest<ObserveTickerUpdatesUseCase>() {

    private val mainRepository: MainRepository = mockk(relaxed = true)

    override fun initSelf(): ObserveTickerUpdatesUseCase {
        return ObserveTickerUpdatesUseCase(mainRepository)
    }

    @Test
    fun `should return a TickerUiModel`() = runBlocking {

        coEvery { mainRepository.observeTicker() } returns flowOf(appleTickerUiModel)

        val result = systemUnderTest().first()

        assertEquals(appleTickerUiModel, result)
    }

}