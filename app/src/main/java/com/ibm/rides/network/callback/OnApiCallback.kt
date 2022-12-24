package com.ibm.rides.network.callback

interface OnApiCallback<T> {
    fun onSuccess(t: T)
    fun onFailure(error: String)
}