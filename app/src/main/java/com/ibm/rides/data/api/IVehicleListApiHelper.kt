package com.ibm.rides.data.api

import com.ibm.rides.ui.vehicle.model.Vehicle
import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody
import javax.inject.Inject

interface IVehicleListApiHelper {

    suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody>
}

class VehicleListApiHelperImpl @Inject constructor(private val apiService: ApiService) : IVehicleListApiHelper {

    override suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> = apiService.fetchVehicle(size)

}