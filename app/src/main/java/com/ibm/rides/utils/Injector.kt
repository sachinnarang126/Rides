package com.ibm.rides.utils

import android.app.Application
import com.ibm.rides.module.vehicle.detail.VehicleDetailViewModel
import com.ibm.rides.module.vehicle.list.VehicleListingViewModel
import com.ibm.rides.module.vehicle.userinput.UserInputViewModel

object Injector {

    fun getUserInputVMFactory(
        application: Application
    ): UserInputViewModel.Factory =
        UserInputViewModel.Factory(
            application
        )

    fun getVehicleListingVMFactory(
        application: Application
    ): VehicleListingViewModel.Factory =
        VehicleListingViewModel.Factory(application)

    fun getVehicleDetailVMFactory(
        application: Application
    ): VehicleDetailViewModel.Factory =
        VehicleDetailViewModel.Factory(application)

    /*private fun getRepository(application: Application): Repository {
        val preference = PreferenceData.getInstance(application)
        return Repository.getInstance(preference)
    }

    */

}