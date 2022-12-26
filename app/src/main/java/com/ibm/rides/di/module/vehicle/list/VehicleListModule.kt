package com.ibm.rides.di.module.vehicle.list

import com.ibm.rides.data.api.IVehicleListApiHelper
import com.ibm.rides.data.api.VehicleListApiHelperImpl
import com.ibm.rides.data.repository.VehicleListRepository
import com.ibm.rides.di.module.coroutines.IoDispatcher
import com.ibm.rides.domain.vehicle.list.IVehicleListUseCase
import com.ibm.rides.domain.vehicle.list.VehicleListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
class VehicleListModule {

    @Provides
    @Reusable
    fun provideVehicleListUseCase(repository: VehicleListRepository, @IoDispatcher ioDispatcher: CoroutineDispatcher): IVehicleListUseCase {
        return VehicleListUseCaseImpl(repository, ioDispatcher)
    }

    @Provides
    @Reusable
    fun provideVehicleListApiHelper(apiHelper: VehicleListApiHelperImpl): IVehicleListApiHelper = apiHelper
}