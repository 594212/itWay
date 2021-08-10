package com.way.august

import androidx.lifecycle.MutableLiveData
import com.rengwuxian.materialedittext.MaterialEditText

class AuthViewModel {

    val state = MutableLiveData<LoginState>().default(initiaValue = LoginState.DefaultState())

    fun login(email: String, pass: String) {
         state.set(newValue = LoginState.SucceededState())

    }
    fun register(email: String, pass: String, name: String, phone: String) {

    }

}
