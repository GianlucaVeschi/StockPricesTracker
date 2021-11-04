package com.gianlucaveschi.stockpricestracker.ui.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutManager(
    context: Context,
    private val animateItems: Boolean
) : LinearLayoutManager(context) {
    override fun supportsPredictiveItemAnimations(): Boolean {
        return if (animateItems) true else super.supportsPredictiveItemAnimations()
    }
}