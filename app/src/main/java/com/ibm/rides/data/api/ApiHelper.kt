package com.ibm.rides.data.api

import com.ibm.rides.ui.vehicle.model.Vehicle
import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody

interface ApiHelper {

    suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody>
}