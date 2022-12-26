package com.ibm.rides

import com.ibm.rides.ui.vehicle.detail.CarbonEmissionCalculator
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CarbonEmissionCalculationUnitTest {

    @Inject lateinit var carbonEmissionCalculator: CarbonEmissionCalculator

    @Test
    fun `Assert total carbon emission calculation`() {
        val expectedCarbon = 6500.0
        val actualCarbon = carbonEmissionCalculator.calculateTotalCarbonEmission(6000)
        Assert.assertEquals(expectedCarbon, actualCarbon, 0.0)
    }

    @Test
    fun `Assert estimated carbon emission calculation`() {
        val expectedEstimatedCarbon = 1.08
        val actualCarbon = carbonEmissionCalculator.calculateEstimateCarbonEmission(6000, 6500.0)
        Assert.assertEquals(expectedEstimatedCarbon, actualCarbon, 0.09)
    }
}