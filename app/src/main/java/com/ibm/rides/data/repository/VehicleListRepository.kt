package com.ibm.rides.data.repository

import com.ibm.rides.data.api.IVehicleListApiHelper
import com.ibm.rides.data.response.NetworkResponse
import com.ibm.rides.ui.vehicle.model.Vehicle
import okhttp3.ResponseBody
import javax.inject.Inject

class VehicleListRepository @Inject constructor(private val apiHelper: IVehicleListApiHelper) {

    suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> = apiHelper.fetchVehicle(size)
}