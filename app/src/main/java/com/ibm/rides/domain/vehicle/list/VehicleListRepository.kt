package com.ibm.rides.domain.vehicle.list

import com.ibm.rides.data.api.ApiService
import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class VehicleListRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> = apiService.fetchVehicle(size)
}