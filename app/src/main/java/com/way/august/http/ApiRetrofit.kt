package com.way.august.http

import com.way.august.http.common.RequestLog
import com.way.august.http.common.RequestReg
import com.way.august.http.common.ResponseAuth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface ApiRetrofit {

    @POST("api/login")
    @Headers("Accept: application/json")
    suspend fun login(@Body requestLogin:RequestLog): Call<ResponseAuth>

    @POST("api/register")
    @Headers("Accept: application/json") //intersepter
    suspend fun register(@Body requestReg: RequestReg): Call<ResponseAuth>

}


