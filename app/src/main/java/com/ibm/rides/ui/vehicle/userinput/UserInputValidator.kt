package com.ibm.rides.ui.vehicle.userinput

class UserInputValidator private constructor() {

    companion object {
        fun isValidVehicleCount(vehicleCount: Int): Boolean {
            return vehicleCount in 1..100
        }
    }
}