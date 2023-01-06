package com.ibm.rides.ui.vehicle.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.LargeTest
import com.ibm.rides.data.response.NetworkResponse
import com.ibm.rides.domain.vehicle.list.IVehicleListUseCase
import com.ibm.rides.utils.NetworkHelper
import com.ibm.rides.utils.Util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.random.Random

@LargeTest
@RunWith(RobolectricTestRunner::class)
class VehicleListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: IVehicleListUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getRides() = runTest {
        val size = Random.nextInt(1, 100)
        Mockito.`when`(useCase.invoke(size)).thenReturn(NetworkResponse.Success(emptyList(), 200))
        val viewModel = VehicleListViewModel(useCase, NetworkHelper(RuntimeEnvironment.getApplication()))
        viewModel.fetchVehicleList(size)
        testDispatcher.scheduler.advanceUntilIdle()
        val response = viewModel.vehicleList.getOrAwaitValue(20)

        Assert.assertEquals(0, response.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}