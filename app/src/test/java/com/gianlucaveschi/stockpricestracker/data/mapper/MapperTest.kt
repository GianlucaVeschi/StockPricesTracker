package com.gianlucaveschi.stockpricestracker.data.mapper

import com.gianlucaveschi.stockpricestracker.testutils.Shared.appleTickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MapperTest {

    private val tickerApiModel = TickerApiModel(
        APPLE_ISIN,
        1.00,
        2.00,
        3.00
    )

    @Test
    fun `should map TickerApiModel to TickerUiModel`() {
        val expected = TickerUiModel(appleTickerUiModel.name, appleTickerUiModel.isin, 1.00)
        val result = tickerApiModel.mapToUiModel()
        assertEquals(expected,result)
    }

    @Test
    fun `should fail to map TickerApiModel to TickerUiModel`() {
        val expected = TickerUiModel(appleTickerUiModel.name, appleTickerUiModel.isin, 2.00)
        val result = tickerApiModel.mapToUiModel()
        assertNotEquals(expected,result)
    }

    @Test
    fun `should map TickerUiModel to TickerSubscription`() {
        val expected = TickerSubscription(APPLE_ISIN)
        val result: TickerSubscription = appleTickerUiModel.mapToTicketSubscription()
        assertEquals(expected, result)
    }

    @Test
    fun `should fail to map TickerUiModel to TickerSubscription`() {
        val expected = TickerSubscription(WRONG_ISIN)
        val result: TickerSubscription = appleTickerUiModel.mapToTicketSubscription()
        assertNotEquals(expected, result)
    }

    @Test
    fun `should map TickerUiModel to TickerUnsubscription`() {
        val expected = TickerUnsubscription(APPLE_ISIN)
        val result: TickerUnsubscription = appleTickerUiModel.mapToTicketUnsubscription()
        assertEquals(expected, result)
    }

    @Test
    fun `should fail to map TickerUiModel to TickerUnsubscription`() {
        val expected = TickerSubscription(WRONG_ISIN)
        val result: TickerSubscription = appleTickerUiModel.mapToTicketSubscription()
        assertNotEquals(expected, result)
    }

    companion object {
        const val APPLE_ISIN = "US0378331005"
        const val WRONG_ISIN = "wrong"
    }
}