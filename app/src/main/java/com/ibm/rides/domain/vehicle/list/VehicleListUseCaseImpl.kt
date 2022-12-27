package com.ibm.rides.domain.vehicle.list

import com.ibm.rides.data.response.NetworkResponse
import okhttp3.ResponseBody
import javax.inject.Inject

interface IVehicleListUseCase {

    suspend operator fun invoke(size: Int): NetworkResponse<List<Vehicle>, ResponseBody>
}

class VehicleListUseCaseImpl @Inject constructor(private val repository: VehicleListRepository) : IVehicleListUseCase {

    override suspend fun invoke(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> {
        return repository.fetchVehicle(size)
    }

}