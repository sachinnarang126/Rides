package com.ibm.rides.di.module.vehicle.list

import com.ibm.rides.data.api.IVehicleListApiHelper
import com.ibm.rides.data.api.VehicleListApiHelperImpl
import com.ibm.rides.data.repository.VehicleListRepository
import com.ibm.rides.di.module.coroutines.IoDispatcher
import com.ibm.rides.domain.vehicle.list.IVehicleListUseCase
import com.ibm.rides.domain.vehicle.list.VehicleListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class VehicleListModule {

    @Provides
    fun provideVehicleListUseCase(repository: VehicleListRepository, @IoDispatcher ioDispatcher: CoroutineDispatcher): IVehicleListUseCase {
        return VehicleListUseCaseImpl(repository, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideVehicleListApiHelper(apiHelper: VehicleListApiHelperImpl): IVehicleListApiHelper = apiHelper
}