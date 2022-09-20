package com.ibm.rides.module.vehicle.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ibm.rides.basecontroller.BaseAndroidViewModel
import com.ibm.rides.basecontroller.BaseRepository
import com.ibm.rides.module.vehicle.model.Vehicle
import com.ibm.rides.network.callback.OnApiCallback
import com.ibm.rides.network.response.Status

class VehicleListingViewModel(application: Application) : BaseAndroidViewModel(application),
    OnApiCallback<List<Vehicle>?> {

    private var vehicleCount = 0
    private val vehicleListingRepository = VehicleListingRepository(viewModelScope)
    val vehicleList = MutableLiveData<List<Vehicle>>()

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val application: Application
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VehicleListingViewModel::class.java)) {
                return VehicleListingViewModel(
                    application
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun fetchVehicleList(count: Int, isInternetConnected: Boolean = true) {
        vehicleCount = count
        if (isInternetConnected) {
            networkResponse.postValue(Status.LOADING)
            vehicleListingRepository.fetchVehicleList(count, this)
        } else showInternetError()
    }

    fun fetchVehicleListOnPullToRefresh() {
        networkResponse.postValue(Status.LOADING)
        vehicleListingRepository.fetchVehicleList(vehicleCount, this)
    }

    override fun onDestroy() {
        vehicleListingRepository.cancelNetworkCalls()
    }

    override fun getBaseRepository(): BaseRepository {
        return vehicleListingRepository
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
}