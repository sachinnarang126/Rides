package com.ibm.rides.di.module.vehicle.list

import com.ibm.rides.data.api.ApiService
import com.ibm.rides.domain.vehicle.list.VehicleListRepository
import com.ibm.rides.di.module.coroutines.IoDispatcher
import com.ibm.rides.domain.vehicle.list.IVehicleListUseCase
import com.ibm.rides.domain.vehicle.list.VehicleListRemoteDataSource
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
    fun provideVehicleListUseCase(repository: VehicleListRepository): IVehicleListUseCase {
        return VehicleListUseCaseImpl(repository)
    }

    @Provides
    @Reusable
    fun provideVehicleListRemoteDataSource(api: ApiService, @IoDispatcher ioDispatcher: CoroutineDispatcher): VehicleListRemoteDataSource {
        return VehicleListRemoteDataSource(api, ioDispatcher)
    }
}