package com.ibm.rides.basecontroller

import kotlinx.coroutines.CoroutineScope

@Suppress("DEPRECATION")
abstract class BaseRepository(protected val viewModelScope: CoroutineScope) {

    abstract fun cancelNetworkCalls()
}