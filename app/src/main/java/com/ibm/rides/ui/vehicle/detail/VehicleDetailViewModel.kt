package com.ibm.rides.ui.vehicle.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ibm.rides.basecontroller.BaseViewModel
import com.ibm.rides.ui.vehicle.model.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleDetailViewModel @Inject constructor(): BaseViewModel() {

    val vehicle = MutableLiveData<Vehicle>()

    override fun onDestroy() {
        Log.d("UserInputViewModel", "destroying VM")
    }
}