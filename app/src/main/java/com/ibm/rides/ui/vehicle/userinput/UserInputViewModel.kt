package com.ibm.rides.ui.vehicle.userinput

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ibm.rides.basecontroller.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInputViewModel @Inject constructor() : BaseViewModel() {

    val vehicleCount = MutableLiveData<String>()

    override fun onDestroy() {
        Log.d("UserInputViewModel", "destroying VM")
    }
}