package com.gianlucaveschi.stockpricestracker.domain.mapper

import com.gianlucaveschi.stockpricestracker.domain.getHardcodedTickerInfo
import com.gianlucaveschi.stockpricestracker.domain.model.TickerApiModel
import com.gianlucaveschi.stockpricestracker.domain.model.TickerUiModel

class MapperTickerApiModelToTickerUiModel {

    fun map(from: TickerApiModel): TickerUiModel {
        return from.run {
            TickerUiModel(
                tickerInfo = getHardcodedTickerInfo().first { it.isin == from.isin },
                price = price
            )
        }
    }
}