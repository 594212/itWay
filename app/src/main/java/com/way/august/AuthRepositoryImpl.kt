package com.way.august

import android.util.Log
import com.way.august.http.AppRetrofit
import com.way.august.http.common.RequestLog
import com.way.august.http.common.RequestReg
import com.way.august.http.common.ResponseAuth
import retrofit2.await


interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(
        email: String, password: String,
        name: String, phone: String
    ): ResponseAuth

}

class AuthRepositoryImpl : AuthRepository {
    private val appRetrofit = AppRetrofit.get()

    override suspend fun login(email: String, password: String) {
        val requestLog = RequestLog(email = email, password = password)

        try {
            Log.v("succeeded", appRetrofit.login(requestLog).await().accessToken)
        } catch (e: Throwable) {
            Log.v("error", e.toString())
        }
    }


    override suspend fun register(
        email: String, password: String,
        name: String, phone: String
    ): ResponseAuth {

        val requestReg = RequestReg(email, name, password)
        return appRetrofit.register(requestReg).await()
    }

}



