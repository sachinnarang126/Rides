package com.ibm.rides.module.vehicle.list

import com.ibm.rides.basecontroller.BaseRepository
import com.ibm.rides.module.vehicle.model.Vehicle
import com.ibm.rides.network.callback.OnApiCallback
import com.ibm.rides.network.response.NetworkResponse
import com.ibm.rides.network.service.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VehicleListingRepository(viewModelScope: CoroutineScope) : BaseRepository(viewModelScope){

    private var fetchVehicleJob: Job? = null

    fun fetchVehicleList(count: Int, onApiCallback: OnApiCallback<List<Vehicle>?>) {
        fetchVehicleJob = viewModelScope.launch(Dispatchers.IO) {
            when (val response = ApiClient.apiService.fetchVehicle(count)) {
                is NetworkResponse.Success -> {
                    onApiCallback.onSuccess(response.body)
                }
                is NetworkResponse.ApiError -> {
                    onApiCallback.onFailure("Some error occurred")
                }
                is NetworkResponse.NetworkError -> {
                    onApiCallback.onFailure("Network Error")
                }
                is NetworkResponse.UnknownError -> {
                    onApiCallback.onFailure("Unknown Error")
                }
            }
        }
    }

    override fun cancelNetworkCalls() {
        try {
            fetchVehicleJob?.run {
                cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}