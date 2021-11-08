package com.gianlucaveschi.stockpricestracker

import androidx.annotation.CallSuper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Before

abstract class BaseJunitTest<T : Any> {

    protected lateinit var systemUnderTest: T

    @Before
    @CallSuper
    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    open fun setUp() {
        systemUnderTest = initSelf()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    protected abstract fun initSelf(): T
}