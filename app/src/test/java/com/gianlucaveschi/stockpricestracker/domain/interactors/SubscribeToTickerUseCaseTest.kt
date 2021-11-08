package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SubscribeToTickerUseCaseTest : BaseJunitTest<SubscribeToTickerUseCase>() {

    private val mainRepository: MainRepository = mockk(relaxed = true)

    override fun initSelf() = SubscribeToTickerUseCase(mainRepository)

    @Test
    fun `should subscribe to the given Ticker`() = runBlocking {
        systemUnderTest(appleTickerUiModel)

        verify(exactly = 1) { mainRepository.subscribeToTicker(appleTickerUiModel) }
    }

}