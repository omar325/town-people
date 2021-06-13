package com.example.android.townpeople.presentaition.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {
    val IO: CoroutineDispatcher

    class Default : AppDispatchers {
        override val IO = Dispatchers.IO
    }
}