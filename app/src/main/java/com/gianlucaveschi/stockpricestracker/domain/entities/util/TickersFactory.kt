package com.gianlucaveschi.stockpricestracker.domain.entities.util

import com.gianlucaveschi.stockpricestracker.domain.entities.ui.TickerUiModel


fun getHardcodedTickerUiModel(): MutableList<TickerUiModel> = mutableListOf(
    TickerUiModel("Apple Inc.", "US0378331005", null),
    TickerUiModel("Microsoft Corp.", "US5949181045", null),
    TickerUiModel("Invesco Ltd.", "BMG491BT1088", null),
    TickerUiModel("Tesla Motors Inc.", "US88160R1014", null),
    TickerUiModel("Tiffany & Co.", "US8865471085", null),
    TickerUiModel("Amazon.com Inc.", "US0231351067", null),
    TickerUiModel("Nike, Inc.", "US6541061031", null),
    TickerUiModel("Kellogg Co.", "US4878361082", null),
    TickerUiModel("JPMorgan & Co.", "US46625H1005", null),
    TickerUiModel("Google", "US02079K3059", null),
    TickerUiModel("Xiaomi Corp.", "KYG9830T1067", null),
    TickerUiModel("Samsung Electronic & Co.", "KR7005930003"),
    TickerUiModel("Eni S.P.A.", "IT0003132476", null)
)