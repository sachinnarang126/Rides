package com.ibm.rides.basecontroller

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibm.rides.data.response.Status

abstract class BaseViewModel : ViewModel(), Observable {

    private val internetErrorMsg = "Please check your Internet"
    val message = MutableLiveData<String>()
    var networkResponse = MutableLiveData<Status>(null)

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    abstract fun onDestroy()

    /*
    abstract fun getBaseRepository(): BaseRepository?*/

    protected fun showSnackBar(message: String) {
        this.message.postValue(message)
    }

    protected fun showInternetError() {
        this.message.postValue(internetErrorMsg)
    }
}

interface OnApiCallback<T> {
    fun onSuccess(t: T)
    fun onFailure(error: String)
}