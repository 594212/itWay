package com.way.august.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppRetrofit {
    private val BASE_URL = "http://it-cabinet.tk/"
    private val retrofit: ApiRetrofit by lazy(
        ::configurateRetrofit
    )

    private fun configurateRetrofit(): ApiRetrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiRetrofit::class.java)

    }

    fun get(): ApiRetrofit {
        return retrofit
    }

}


