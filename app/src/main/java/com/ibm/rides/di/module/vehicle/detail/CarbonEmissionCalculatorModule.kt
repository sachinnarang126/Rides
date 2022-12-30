package com.ibm.rides.di.module.vehicle.detail

import com.ibm.rides.ui.vehicle.detail.CarbonEmissionCalculator
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class CarbonEmissionCalculatorModule {

    @Provides
    @Reusable
    fun provideCarbonEmissionCalculator(): CarbonEmissionCalculator {
        return CarbonEmissionCalculator()
    }
}