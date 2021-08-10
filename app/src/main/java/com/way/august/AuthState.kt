package com.way.august

import androidx.lifecycle.MutableLiveData

sealed class AuthState {
    class DefaultState: AuthState()
    class SendingState: AuthState()
    class SucceededState:AuthState()
    class ErrorState<Any>(val message:Any): AuthState()
}


fun <T:Any> MutableLiveData<T>.default(initiaValue: T) = apply{setValue(initiaValue)}
fun <T:Any> MutableLiveData<T>.set(newValue: T) = apply{setValue(newValue)}