package com.ibm.rides.ui.vehicle.detail

import javax.inject.Inject

class CarbonEmissionCalculator @Inject constructor() {

    fun calculateTotalCarbonEmission(kilometer: Int?): Double {
        var carbonEmission = 0.0

        kilometer?.let { km ->
            carbonEmission = if (km > 5000) {
                val remainingKM = km - 5000
                5000 + (remainingKM * 1.5)
            } else {
                kilometer.toDouble()
            }
        }

        return carbonEmission
    }

    fun calculateEstimateCarbonEmission(km: Int, estimated: Double): Double {
        if (km == 0) return 0.0
        return estimated / km
    }
}