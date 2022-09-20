package com.ibm.rides.module.vehicle.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibm.rides.basecontroller.BaseAndroidViewModel
import com.ibm.rides.basecontroller.BaseRepository
import com.ibm.rides.module.vehicle.model.Vehicle

class VehicleDetailViewModel(application: Application) : BaseAndroidViewModel(application) {

    val vehicle = MutableLiveData<Vehicle>()

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val application: Application
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VehicleDetailViewModel::class.java)) {
                return VehicleDetailViewModel(
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