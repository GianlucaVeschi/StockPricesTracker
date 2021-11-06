package com.gianlucaveschi.stockpricestracker.domain.interactors

import com.gianlucaveschi.stockpricestracker.BaseJunitTest
import com.gianlucaveschi.stockpricestracker.Shared
import com.gianlucaveschi.stockpricestracker.data.repo.MainRepository
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UnsubscribeToTickerUseCaseTest : BaseJunitTest<UnsubscribeFromTickerUseCase>() {

    private val mainRepository: MainRepository = mockk(relaxed = true)

    override fun initSelf() = UnsubscribeFromTickerUseCase(mainRepository)

    @Test
    fun `should subscribe to the given Ticker`() = runBlocking {
        tested(Shared.appleTickerUiModel)

        verify(exactly = 1) { mainRepository.unsubscribeFromTicker(Shared.appleTickerUiModel) }
    }

}