package com.ibm.rides

import com.google.common.truth.Truth.assertThat
import com.ibm.rides.ui.vehicle.detail.CarbonEmissionCalculator
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@HiltAndroidTest
class CarbonEmissionCalculationUnitTest {

    @Mock
    lateinit var carbonEmissionCalculator: CarbonEmissionCalculator

    @Before
    fun setup(){
//        carbonEmissionCalculator = CarbonEmissionCalculator()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `Assert total carbon emission calculation`() {
        Mockito.`when`(carbonEmissionCalculator.calculateTotalCarbonEmission(anyInt())).thenReturn(6500.0)
        val expectedCarbon = 6500.0
        val actualCarbon = carbonEmissionCalculator.calculateTotalCarbonEmission(6000)
//        Assert.assertEquals(expectedCarbon, actualCarbon, 0.0)
        assertThat(expectedCarbon).isEqualTo(actualCarbon)
    }

    @Test
    fun `Assert estimated carbon emission calculation`() {
        Mockito.`when`(carbonEmissionCalculator.calculateEstimateCarbonEmission(anyInt(), anyDouble())).thenReturn(1.08)
        val expectedEstimatedCarbon = 1.08
        val actualCarbon = carbonEmissionCalculator.calculateEstimateCarbonEmission(6000, 6500.0)
//        Assert.assertEquals(expectedEstimatedCarbon, actualCarbon, 0.09)
        assertThat(expectedEstimatedCarbon).isEqualTo(actualCarbon)
    }
}