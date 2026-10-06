package com.example.pacetride.data.injection

import com.example.pacetride.data.datasource.services.CarreraRetrofitService
import com.example.pacetride.data.datasource.services.ResenaRetrofitService
import com.example.pacetride.data.datasource.services.UsuarioRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesResenaRetrofitService(retrofit: Retrofit): ResenaRetrofitService {
        return retrofit.create(ResenaRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun providesCarreraRetrofitService(retrofit: Retrofit): CarreraRetrofitService {
        return retrofit.create(CarreraRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun providesUsuariosRetrofitService(retrofit: Retrofit): UsuarioRetrofitService {
        return retrofit.create(UsuarioRetrofitService::class.java)
    }
}