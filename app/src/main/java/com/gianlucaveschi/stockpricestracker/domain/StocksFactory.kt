package com.gianlucaveschi.stockpricestracker.domain

import com.gianlucaveschi.stockpricestracker.domain.model.TickerInfo
import com.gianlucaveschi.stockpricestracker.domain.model.Ticker


fun getTickersList() = getHardcodedTickerInfo().map { Ticker(it) }

private fun getHardcodedTickerInfo(): List<TickerInfo> = listOf(
    TickerInfo("Microsoft Corp.", "US5949181045"),
    TickerInfo("Invesco Ltd.", "BMG491BT1088"),
    TickerInfo("Apple Inc.", "US0378331005"),
    TickerInfo("Tesla Motors Inc.", "US88160R1014"),
    TickerInfo("Tiffany & Co.", "US8865471085"),
    TickerInfo("Amazon.com Inc.", "US0231351067"),
    TickerInfo("Nike, Inc.", "US6541061031"),
    TickerInfo("Kellogg Co.", "US4878361082"),
    TickerInfo("JPMorgan & Co.", "US46625H1005"),
    TickerInfo("Microsoft Corp.", "US5949181045"),
    TickerInfo("Invesco Ltd.", "BMG491BT1088"),
    TickerInfo("Apple Inc.", "US0378331005"),
    TickerInfo("Tesla Motors Inc.", "US88160R1014"),
    TickerInfo("Tiffany & Co.", "US8865471085"),
    TickerInfo("Amazon.com Inc.", "US0231351067"),
    TickerInfo("Nike, Inc.", "US6541061031"),
    TickerInfo("Kellogg Co.", "US4878361082"),
    TickerInfo("JPMorgan & Co.", "US46625H1005"),
    TickerInfo("Google", "US02079K3059"),
    TickerInfo("Xiaomi Corp.", "KYG9830T1067"),
    TickerInfo("Samsung Electronic & Co.", "KR7005930003")
)



