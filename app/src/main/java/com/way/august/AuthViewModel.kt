package com.way.august

import androidx.lifecycle.MutableLiveData
import com.way.august.http.common.ResponseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthViewModel {

    val state = MutableLiveData<AuthState>().default(initiaValue = AuthState.DefaultState())
    val AuthRepositoryImpl = AuthRepositoryImpl()

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
        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = AuthRepositoryImpl.login(email = email,
                password = pass)
            if(errorMessage is ResponseAuth){
                launch { state.set(newValue = AuthState.SucceededState()) }
            }else{
                launch { state.set(newValue = AuthState.ErrorState(message = errorMessage)) }
            }

        }

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
        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = AuthRepositoryImpl.register(email = email, password = pass,
            phone = phone, name = name)

            if(errorMessage is ResponseAuth){
                launch { state.set(newValue = AuthState.SucceededState()) }
            }else{
                launch { state.set(newValue = AuthState.ErrorState(message = errorMessage)) }
            }

        }

    }



    private fun validatePass(pass: String): Boolean {
        return (pass.length > 5)
    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@")&&email.contains(".")
    }

}
