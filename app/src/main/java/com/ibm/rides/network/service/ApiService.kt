package com.ibm.rides.network.service


import com.ibm.rides.module.vehicle.model.Vehicle
import com.ibm.rides.network.response.NetworkResponse
import com.ibm.rides.utils.Constant
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(Constant.RANDOM_VEHICLE_URL)
    suspend fun fetchVehicle(@Query("size") size:Int
    ): NetworkResponse<List<Vehicle>, ResponseBody>
}