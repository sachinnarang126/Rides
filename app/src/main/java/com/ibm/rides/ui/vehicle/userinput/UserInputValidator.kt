package com.ibm.rides.ui.vehicle.userinput

import javax.inject.Inject

class UserInputValidator @Inject constructor() {

    fun isValidVehicleCount(vehicleCount: Int): Boolean {
        return vehicleCount in 1..100
    }
}