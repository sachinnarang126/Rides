package com.ibm.rides.data.api

import com.google.gson.GsonBuilder
import com.ibm.rides.BuildConfig
import com.ibm.rides.data.response.NetworkResponseAdapterFactory
import com.ibm.rides.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {

        private var mClient: OkHttpClient? = null
        private var mGsonConverter: GsonConverterFactory? = null

        /**
         * Don't forget to remove Interceptors (or change Logging Level to NONE)
         * in production! Otherwise people will be able to see your request and response on Log Cat.
         */
        private val client: OkHttpClient
            @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
            get() {
                if (mClient == null) {
                    val interceptor = HttpLoggingInterceptor()
                    if (BuildConfig.DEBUG) interceptor.level =
                        HttpLoggingInterceptor.Level.BODY
                    else interceptor.level =
                        HttpLoggingInterceptor.Level.NONE

                    val httpBuilder = OkHttpClient.Builder()
                    httpBuilder
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                    mClient = httpBuilder.build()

                }
                return mClient!!
            }


        private val gsonConverter: GsonConverterFactory
            get() {
                if (mGsonConverter == null) {
                    mGsonConverter = GsonConverterFactory
                        .create(
                            GsonBuilder()
                                .setLenient()
                                .disableHtmlEscaping()
                                .create()
                        )
                }
                return mGsonConverter!!
            }

        private fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .addConverterFactory(
                    gsonConverter
                ).client(client)
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }

        val apiService: ApiService by lazy {
            create()
        }
    }
}