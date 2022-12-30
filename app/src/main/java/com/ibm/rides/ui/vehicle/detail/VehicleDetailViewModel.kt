package com.ibm.rides.ui.vehicle.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ibm.rides.basecontroller.BaseViewModel
import com.ibm.rides.domain.vehicle.list.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleDetailViewModel @Inject constructor() : BaseViewModel() {

    val vehicle = MutableLiveData<Vehicle>()

    @Inject lateinit var carbonEmissionCalculator: CarbonEmissionCalculator

    override fun onDestroy() {
        Log.d("UserInputViewModel", "destroying VM")
    }

    fun calculateTotalCarbonEmission(kilometre: Int?) = carbonEmissionCalculator.calculateTotalCarbonEmission(kilometre)

    fun calculateEstimateCarbonEmission(km: Int, estimated: Double) = carbonEmissionCalculator.calculateEstimateCarbonEmission(km, estimated)
}