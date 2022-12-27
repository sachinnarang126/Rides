package com.ibm.rides.domain.vehicle.list

import com.ibm.rides.data.response.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

interface IVehicleListUseCase {

    suspend operator fun invoke(size: Int): NetworkResponse<List<Vehicle>, ResponseBody>
}

class VehicleListUseCaseImpl @Inject constructor(private val repository: VehicleListRepository,
                                                 private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) : IVehicleListUseCase {

    override suspend fun invoke(size: Int): NetworkResponse<List<Vehicle>, ResponseBody> {
        return withContext(defaultDispatcher) {
            repository.fetchVehicle(size)
        }
    }

}