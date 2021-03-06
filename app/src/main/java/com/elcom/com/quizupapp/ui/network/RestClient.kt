package com.elcom.com.quizupapp.ui.network

import com.elcom.com.quizupapp.utils.ConstantsApp
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by admin on 3/8/2018.
 */
class RestClient {
    private var restService: RestService? = null
    private var restServiceCompany: RestService? = null
    private val restServiceTest: RestService? = null
    private var instance: RestClient? = null

    fun getInstance(): RestClient {
        if (instance == null) {
            instance = RestClient()


            return instance as RestClient
        }
        return instance as RestClient
    }

    fun getRestService(): RestService {
        if (restService == null) {
            val builder = OkHttpClient().newBuilder()
            builder.addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                        .addHeader("Authorization", "Bearer "+ConstantsApp.BASE64_HEADER)
                        .build()

                chain.proceed(request)
            }
            builder.readTimeout(20, TimeUnit.SECONDS)
            builder.connectTimeout(15, TimeUnit.SECONDS)
            builder.writeTimeout(30, TimeUnit.SECONDS)
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(ConstantsApp.SERVER_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            restService = retrofit.create(RestService::class.java!!)
            return restService as RestService
        }
        return restService as RestService
    }

    fun getRestServiceCompany(): RestService? {

        if (restServiceCompany == null) {
            val builder = OkHttpClient().newBuilder()

            builder.addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                        .header("X-DEVICE", ConstantsApp.BASE64_HEADER)
                        .header("X-AUTH-TOKEN", ConstantsApp.BASE64_AUTH_TOKEN)
                        .build()

                chain.proceed(request)
            }

            builder.readTimeout(20, TimeUnit.SECONDS)
            builder.connectTimeout(15, TimeUnit.SECONDS)
            builder.writeTimeout(30, TimeUnit.SECONDS)
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(ConstantsApp.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            restServiceCompany = retrofit.create(RestService::class.java!!)
            return restServiceCompany
        }
        return restServiceCompany
    }
}