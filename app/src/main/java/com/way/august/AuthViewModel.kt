package com.way.august

import androidx.lifecycle.MutableLiveData
import com.rengwuxian.materialedittext.MaterialEditText

class AuthViewModel {

    val state = MutableLiveData<AuthState>().default(initiaValue = AuthState.DefaultState())

    fun login(email: String, pass: String) {
         state.set(newValue = AuthState.SucceededState())

    }

    fun register(email: String, pass: String, name: String, phone: String) {
            state.set(newValue = AuthState.SucceededState())
    }

}
