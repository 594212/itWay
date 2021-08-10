package com.way.august

import androidx.lifecycle.MutableLiveData

sealed class LoginState {
    class DefaultState: LoginState()
    class SendingState: LoginState()
    class SucceededState:LoginState()
    class ErrorState<Any>(val message:Any): LoginState()
}


sealed class RegisterState {
    class DefaultState: LoginState()
    class SendingState: LoginState()
    class SucceededState:LoginState()
    class ErrorState<Any>(val message:Any): LoginState()

}

fun <T:Any> MutableLiveData<T>.default(initiaValue: T) = apply{setValue(initiaValue)}
fun <T:Any> MutableLiveData<T>.set(newValue: T) = apply{setValue(newValue)}