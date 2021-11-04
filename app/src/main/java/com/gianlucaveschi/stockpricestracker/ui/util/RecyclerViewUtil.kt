package com.gianlucaveschi.stockpricestracker.ui.util

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.stockpricestracker.R

fun RecyclerView.setup(removeDivider: Boolean = false, animateItems: Boolean = false) {
    layoutManager = LinearLayoutManager(context, animateItems)

    if (!removeDivider) {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.recyclerview_divider
            )!!
        )
        addItemDecoration(itemDecorator)
    }
}
