package com.ibm.rides

import com.ibm.rides.module.vehicle.detail.CarbonEmissionCalculator
import org.junit.Assert
import org.junit.Test


class CarbonEmissionCalculationUnitTest {

    @Test
    fun `Assert total carbon emission calculation`() {
        val expectedCarbon = 6500.0
        val actualCarbon = CarbonEmissionCalculator.calculateTotalCarbonEmission(6000)
        Assert.assertEquals(expectedCarbon, actualCarbon, 0.0)
    }

    @Test
    fun `Assert estimated carbon emission calculation`() {
        val expectedEstimatedCarbon = 1.08
        val actualCarbon = CarbonEmissionCalculator.calculateEstimateCarbonEmission(6000, 6500.0)
        Assert.assertEquals(expectedEstimatedCarbon, actualCarbon, 0.09)
    }
}