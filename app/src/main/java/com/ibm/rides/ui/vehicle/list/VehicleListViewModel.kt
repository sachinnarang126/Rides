package com.ibm.rides.ui.vehicle.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibm.rides.basecontroller.BaseViewModel
import com.ibm.rides.basecontroller.OnApiCallback
import com.ibm.rides.data.response.NetworkResponse
import com.ibm.rides.data.response.Status
import com.ibm.rides.domain.vehicle.list.IVehicleListUseCase
import com.ibm.rides.domain.vehicle.list.Vehicle
import com.ibm.rides.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(private val vehicleListUseCase: IVehicleListUseCase, private val networkHelper: NetworkHelper) :
    BaseViewModel(), OnApiCallback<List<Vehicle>?> {

    private var vehicleCount = 0
    val vehicleList = MutableLiveData<List<Vehicle>>()

    fun fetchVehicleList(count: Int) {
        vehicleCount = count
        if (networkHelper.isNetworkConnected()) {
            networkResponse.postValue(Status.LOADING)
            viewModelScope.launch {
                notifyUI(vehicleListUseCase(count))
            }
        } else showInternetError()
    }

    fun fetchVehicleListOnPullToRefresh() {
        networkResponse.postValue(Status.LOADING)
        viewModelScope.launch {
            notifyUI(vehicleListUseCase(vehicleCount))
        }
    }

    private fun notifyUI(response: NetworkResponse<List<Vehicle>, ResponseBody>) {
        when (response) {
            is NetworkResponse.Success      -> {
                onSuccess(response.body)
            }
            is NetworkResponse.ApiError     -> {
                onFailure("Some error occurred")
            }
            is NetworkResponse.NetworkError -> {
                onFailure("Network Error")
            }
            is NetworkResponse.UnknownError -> {
                onFailure("Unknown Error")
            }
        }
    }

    override fun onSuccess(t: List<Vehicle>?) {
        try {
            t?.let { vehicles ->
                vehicleList.postValue(vehicles.sortedWith(compareBy { it.vin }))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            networkResponse.postValue(Status.SUCCESS)
        }
    }

    override fun onFailure(error: String) {
        networkResponse.postValue(Status.ERROR)
        showSnackBar(error)
    }

    override fun onDestroy() {

    }
}