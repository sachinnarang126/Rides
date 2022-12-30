package com.ibm.rides

import com.ibm.rides.ui.vehicle.userinput.UserInputValidator
import com.ibm.rides.ui.vehicle.userinput.UserInputViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random

@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class UserInputValidationUnitTest {


    private lateinit var viewModel: UserInputViewModel

    private lateinit var validator: UserInputValidator

    @Before
    fun setup() {
        validator = UserInputValidator()
        viewModel = UserInputViewModel(validator)
    }

    @Test
    fun `Assert user input validation`() {
        val expectedValidCount = true
//        val isValidCount = UserInputValidator().isValidVehicleCount(Random.nextInt(101, 1000))
        val isValidCount = viewModel.isValidVehicleCount(Random.nextInt(1, 100))
        Assert.assertEquals(expectedValidCount, isValidCount)
    }
}