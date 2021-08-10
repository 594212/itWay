package com.way.august

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

sealed class AuthState {
    class DefaultState: AuthState()
    class SendingState: AuthState()
    class SucceededState:AuthState()
    class ErrorState<Any>(val message:Any): AuthState()
}


fun <T:Any> MutableLiveData<T>.default(initiaValue: T) = apply{setValue(initiaValue)}
fun <T:Any> MutableLiveData<T>.set(newValue: T) = apply{setValue(newValue)}


suspend fun <T> Call<T>.await():T = suspendCoroutine { continuation ->
    enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful){
                continuation.resume(response.body()!!)
            }else{
                continuation.resumeWithException(Throwable(response.errorBody().toString()))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            continuation.resumeWithException(t)
        }

    })

}