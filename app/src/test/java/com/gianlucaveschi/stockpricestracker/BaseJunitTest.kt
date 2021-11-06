package com.gianlucaveschi.stockpricestracker

import androidx.annotation.CallSuper
import org.junit.Before

abstract class BaseJunitTest<T : Any> {

    protected lateinit var tested: T

    @Before
    @CallSuper
    open fun setUp() {
        tested = initSelf()
    }

    protected abstract fun initSelf(): T
}