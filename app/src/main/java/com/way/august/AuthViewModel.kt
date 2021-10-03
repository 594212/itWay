package com.way.august

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.way.august.http.common.ResponseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    val state = MutableLiveData<AuthState>().default(initiaValue = AuthState.DefaultState())
    private val authRepositoryImpl = AuthRepositoryImpl()

    fun login(email: String, pass: String) {
        if (!validateEmail(email = email)) {
            state.set(newValue = AuthState.ErrorState<Int>(message = R.string.email_invalid))
            return
        }
        if (!validatePass(pass = pass)) {
            state.set(newValue = AuthState.ErrorState<Int>(message = R.string.auth_validate_faild))
            return
        }

        //connect DB
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.v("succeeded token:","I'm here")
//
//            val errorMessage = authRepositoryImpl.login(email = email,
//                password = pass)
//            Log.v("succeeded token:", errorMessage.accessToken)
//
//            if (errorMessage is ResponseAuth){
//                launch(Dispatchers.Main) {
//                    Log.v("succeeded token:", errorMessage.accessToken)
//                    state.set(newValue = AuthState.SucceededState()) }
//            }else{
//                launch(Dispatchers.Main) { state.set(newValue = AuthState.ErrorState(message = errorMessage)) }
//            }
//
//        }

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
            val errorMessage = authRepositoryImpl.register(email = email, password = pass,
            phone = phone, name = name)

            if(errorMessage is ResponseAuth){
                launch(Dispatchers.Main) { state.set(newValue = AuthState.SucceededState()) }
            }else{
                launch(Dispatchers.Main) { state.set(newValue = AuthState.ErrorState(message = errorMessage)) }
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
