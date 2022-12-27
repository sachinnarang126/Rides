package com.ibm.rides.domain.vehicle.list

import com.ibm.rides.data.api.ApiService
import com.ibm.rides.data.response.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

class VehicleListRemoteDataSource @Inject constructor(private val api: ApiService,
                                                      private val defaultDispatcher: CoroutineDispatcher) {
    suspend fun fetchVehicle(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> {
        return withContext(defaultDispatcher) {
            api.fetchVehicle(size)
        }
    }
}