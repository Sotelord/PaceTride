package com.example.pacetride.data.injection

import android.content.Context
import com.example.pacetride.data.datasource.LocationDataSource
import com.example.pacetride.data.datasource.impl.FusedLocationDataSourceImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    abstract fun bindLocationDataSource(impl: FusedLocationDataSourceImpl): LocationDataSource

    companion object {
        @Provides
        @Singleton
        fun provideFusedLocationClient(
            @ApplicationContext context: Context
        ): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
    }
}