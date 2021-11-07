package com.gianlucaveschi.stockpricestracker.domain.entities.util


fun Double.shorten(decimals: Int) : String {
    return "€" + String.format("%.$decimals" + "f", this).replace(',', '.')
}

