package com.ibm.rides.module.vehicle.userinput

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibm.rides.basecontroller.BaseAndroidViewModel
import com.ibm.rides.basecontroller.BaseRepository

class UserInputViewModel(application: Application) : BaseAndroidViewModel(application) {

    val vehicleCount = MutableLiveData<String>()

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val application: Application
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserInputViewModel::class.java)) {
                return UserInputViewModel(
                    application
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    override fun onDestroy() {
        Log.d("UserInputViewModel", "destroying VM")
    }

    override fun getBaseRepository(): BaseRepository? {
        return null
    }
}