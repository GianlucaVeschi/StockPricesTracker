package com.gianlucaveschi.stockpricestracker.presentation.util

import android.view.View
import android.view.animation.AlphaAnimation
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

fun View.blink(
    times: Int = 1,
    duration: Long = 50L,
    minAlpha: Float = 0.0f,
    maxAlpha: Float = 1.0f,
) {
    startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
        it.duration = duration
        it.repeatCount = times
    })
}
