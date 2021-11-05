package com.gianlucaveschi.stockpricestracker.domain.mapper

internal interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}