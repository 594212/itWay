package com.way.august

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register_window.view.*
import kotlinx.android.synthetic.main.sign_in_window.view.*
import kotlinx.android.synthetic.main.sign_in_window.view.emailField
import kotlinx.android.synthetic.main.sign_in_window.view.passField


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSignIn.setOnClickListener(){showSingnInWindow()}
        btnRegister.setOnClickListener(){showRegisterWindow()}


    }



    private fun showSingnInWindow() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Войти")
        dialog.setMessage("Введите данные для входа")
        val inflater = LayoutInflater.from(this)
        val signInWindow = inflater.inflate(R.layout.sign_in_window, null)
        dialog.setView(signInWindow)



        dialog.setNegativeButton("Отмена") { dialogInterface, which -> dialogInterface.dismiss() }
        dialog.setPositiveButton("Войти"){
            dialogInterface,which ->
                login(signInWindow.emailField,signInWindow.passField)
                }

        dialog.show()
    }




    private fun showRegisterWindow() {
        val  dialog = AlertDialog.Builder(this)
        dialog.setTitle("Зарегистрироваться")
        dialog.setMessage("Введите все данные для регистрации")

        val inflater = LayoutInflater.from(this)
        val registerWindow = inflater.inflate(R.layout.register_window,null)
        dialog.setView((registerWindow))
        dialog.setNegativeButton("Отмена"){
            dialogInterface, which->
            dialogInterface.dismiss()
        }
        dialog.setPositiveButton("Создать"){
            dialogInterface,which->

            register(registerWindow.emailField,registerWindow.passField,
                    registerWindow.nameField, registerWindow.phoneField)
        }
        dialog.show()

    }



}