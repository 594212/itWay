package com.way.august

import androidx.lifecycle.MutableLiveData


sealed class AuthState {
    class DefaultState: AuthState()
    class SendingState: AuthState()
    class SucceededState:AuthState()
    class ErrorState<Any>(val message:Any): AuthState()
}


fun <T:Any> MutableLiveData<T>.default(initValue: T) = apply{setValue(initValue)}
fun <T:Any> MutableLiveData<T>.set(newValue: T) = apply{setValue(newValue)}
