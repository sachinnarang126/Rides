package com.ibm.rides.data.api

import com.ibm.rides.ui.vehicle.model.Vehicle
import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> = apiService.fetchVehicle(size)

}