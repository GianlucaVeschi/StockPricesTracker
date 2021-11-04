package com.gianlucaveschi.stockpricestracker.domain

import com.gianlucaveschi.stockpricestracker.domain.model.StockModel
import com.gianlucaveschi.stockpricestracker.domain.model.StockPriceModel


fun getStockPriceModels() = getHardcodedStockModels().map { StockPriceModel(it) }

private fun getHardcodedStockModels(): List<StockModel> = listOf(
    StockModel("Microsoft Corp.", "US5949181045"),
    StockModel("Invesco Ltd.", "BMG491BT1088"),
    StockModel("Apple Inc.", "US0378331005"),
    StockModel("Tesla Motors Inc.", "US88160R1014"),
    StockModel("Tiffany & Co.", "US8865471085"),
    StockModel("Amazon.com Inc.", "US0231351067"),
    StockModel("Nike, Inc.", "US6541061031"),
    StockModel("Kellogg Co.", "US4878361082"),
    StockModel("JPMorgan & Co.", "US46625H1005"),
    StockModel("Microsoft Corp.", "US5949181045"),
    StockModel("Invesco Ltd.", "BMG491BT1088"),
    StockModel("Apple Inc.", "US0378331005"),
    StockModel("Tesla Motors Inc.", "US88160R1014"),
    StockModel("Tiffany & Co.", "US8865471085"),
    StockModel("Amazon.com Inc.", "US0231351067"),
    StockModel("Nike, Inc.", "US6541061031"),
    StockModel("Kellogg Co.", "US4878361082"),
    StockModel("JPMorgan & Co.", "US46625H1005"),
    StockModel("Google", "US02079K3059"),
    StockModel("Xiaomi Corp.", "KYG9830T1067"),
    StockModel("Samsung Electronic & Co.", "KR7005930003")
)



