package com.ibm.rides.domain.vehicle.list

import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody
import javax.inject.Inject

class VehicleListUseCase @Inject constructor(private val repository: VehicleListRepository) {

    suspend operator fun invoke(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> {
        return repository.fetchVehicle(size)
    }

}