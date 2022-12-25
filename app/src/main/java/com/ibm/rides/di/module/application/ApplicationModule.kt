package com.ibm.rides.di.module.application

import com.google.gson.GsonBuilder
import com.ibm.rides.BuildConfig
import com.ibm.rides.data.api.ApiHelper
import com.ibm.rides.data.api.ApiHelperImpl
import com.ibm.rides.data.api.ApiService
import com.ibm.rides.data.response.NetworkResponseAdapterFactory
import com.ibm.rides.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = Constant.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().setLenient().disableHtmlEscaping().create())

    @Provides
    @Singleton
    fun provideNetworkResponseAdapterFactory(): NetworkResponseAdapterFactory = NetworkResponseAdapterFactory()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        BASE_URL: String,
                        gsonConverterFactory: GsonConverterFactory,
                        networkResponseAdapterFactory: NetworkResponseAdapterFactory): Retrofit =
        Retrofit.Builder().addCallAdapterFactory(networkResponseAdapterFactory).addConverterFactory(gsonConverterFactory).baseUrl(BASE_URL)
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}