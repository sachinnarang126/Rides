package com.ibm.rides.data.callback

interface OnApiCallback<T> {
    fun onSuccess(t: T)
    fun onFailure(error: String)
}