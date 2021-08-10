package com.way.august

import com.way.august.http.AppRetrofit
import com.way.august.http.common.RequestLog
import com.way.august.http.common.RequestReg
import com.way.august.http.common.ResponseAuth
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface AuthRepository{
    suspend fun login(email: String,password: String):ResponseAuth
    suspend fun register(email: String,password: String,
                         name: String, phone: String):ResponseAuth
}

class AuthRepositoryImpl:AuthRepository {
    val  appRetrofit = AppRetrofit.get()

    override suspend fun login(email:String, password: String):ResponseAuth{
        val requestLog = RequestLog(email = email, password = password)
        var errorMesage: Deferred<String>

        return appRetrofit.login(requestLog).await()
    }


    override suspend fun register(email: String, password: String,
                                  name: String, phone: String):ResponseAuth{
        val requestReg = RequestReg(email, name, password)

        return appRetrofit.register(requestReg).await()
    }
}