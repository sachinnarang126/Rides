package com.ibm.rides

import com.ibm.rides.ui.vehicle.userinput.UserInputValidator
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

@HiltAndroidTest
class UserInputValidationUnitTest {

    @Test
    fun `Assert user input validation`() {
        val expectedValidCount = true
//        val isValidCount = UserInputValidator().isValidVehicleCount(Random.nextInt(101, 1000))
        val isValidCount = UserInputValidator().isValidVehicleCount(Random.nextInt(1, 100))
        Assert.assertEquals(expectedValidCount, isValidCount)
    }
}