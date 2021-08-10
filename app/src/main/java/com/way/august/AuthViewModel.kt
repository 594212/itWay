package com.way.august

import androidx.lifecycle.MutableLiveData

class AuthViewModel {

    val state = MutableLiveData<AuthState>().default(initiaValue = AuthState.DefaultState())

    fun login(email: String, pass: String) {
        if(!validateEmail(email = email)){
            state.set(newValue = AuthState.ErrorState<Int>(message = R.string.email_invalid))
            return
        }
        if (!validatePass(pass = pass)){
            state.set(newValue = AuthState.ErrorState<Int>(message = R.string.auth_validate_faild))
            return
        }

        //connect DB
        state.set(newValue = AuthState.SucceededState())

    }


    fun register(email: String, pass: String, name: String, phone: String) {
        if(!validateEmail(email = email)){
            state.set(newValue = AuthState.ErrorState<Int>(message = R.string.email_invalid))
            return
        }
        if(!validatePass(pass = pass)){
            state.set(newValue = AuthState.ErrorState<Int>(message = R.string.pass_invalid))
            return
        }

        //connect DB
        state.set(newValue = AuthState.SucceededState())

    }



    private fun validatePass(pass: String): Boolean {
        return (pass.length > 5)
    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@")&&email.contains(".")
    }

}
