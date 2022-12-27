package com.ibm.rides.domain.vehicle.list

import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class VehicleListRepository @Inject constructor(private val dataSource: VehicleListRemoteDataSource) {

    suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> = dataSource.fetchVehicle(size)
}