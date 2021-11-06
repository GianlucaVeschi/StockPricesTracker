package com.gianlucaveschi.stockpricestracker.data.mapper

import com.gianlucaveschi.stockpricestracker.domain.entities.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.TickerInfo
import com.gianlucaveschi.stockpricestracker.domain.entities.TickerUiModel
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerSubscription
import com.gianlucaveschi.stockpricestracker.domain.entities.api.TickerUnsubscription
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MapperTest {

    private val appleTickerInfo = TickerInfo("Apple Inc.", APPLE_ISIN)

    private val tickerApiModel = TickerApiModel(
        APPLE_ISIN,
        BigDecimal(123),
        BigDecimal(345),
        BigDecimal(678)
    )

    @Test
    fun `should map TickerApiModel to TickerUiModel`() {
        val expected = TickerUiModel(appleTickerInfo, BigDecimal(123))
        val result = tickerApiModel.mapToUiModel()
        assertEquals(expected,result)
    }

    @Test
    fun `should fail to map TickerApiModel to TickerUiModel`() {
        val expected = TickerUiModel(appleTickerInfo, BigDecimal(234))
        val result = tickerApiModel.mapToUiModel()
        assertNotEquals(expected,result)
    }

    @Test
    fun `should map TickerInfo to TickerSubscription`() {
        val expected = TickerSubscription(APPLE_ISIN)
        val result: TickerSubscription = appleTickerInfo.mapToTicketSubscription()
        assertEquals(expected, result)
    }

    @Test
    fun `should fail to map TickerInfo to TickerSubscription`() {
        val expected = TickerSubscription(WRONG_ISIN)
        val result: TickerSubscription = appleTickerInfo.mapToTicketSubscription()
        assertNotEquals(expected, result)
    }

    @Test
    fun `should map TickerInfo to TickerUnsubscription`() {
        val expected = TickerUnsubscription(APPLE_ISIN)
        val result: TickerUnsubscription = appleTickerInfo.mapToTicketUnsubscription()
        assertEquals(expected, result)
    }

    @Test
    fun `should fail to map TickerInfo to TickerUnsubscription`() {
        val expected = TickerSubscription(WRONG_ISIN)
        val result: TickerSubscription = appleTickerInfo.mapToTicketSubscription()
        assertNotEquals(expected, result)
    }

    companion object {
        const val APPLE_ISIN = "US0378331005"
        const val WRONG_ISIN = "wrong"
    }
}