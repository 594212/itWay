package com.way.august

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val authViewModel = AuthViewModel()

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


        val email: MaterialEditText = signInWindow.findViewById(R.id.emailField)
        val pass: MaterialEditText = signInWindow.findViewById(R.id.passField)


        dialog.setNegativeButton("Отмена") { dialogInterface, which -> dialogInterface.dismiss()}

        dialog.setPositiveButton("Войти"){
            dialogInterface,which ->
            authViewModel.login(email = email.text.toString(),pass = pass.text.toString())
                }


        authViewModel.state.observe(this@MainActivity){state ->
            when(state){
                is LoginState.SucceededState ->{
                    Toast.makeText(dialog.context,"Вход произведен успешно",Toast.LENGTH_LONG).show()
                }

                is LoginState.ErrorState<*>->{
                    when(state.message){
                        is Int -> Toast.makeText(dialog.context,getString(state.message),Toast.LENGTH_LONG).show()
                        is String -> Toast.makeText(dialog.context,state.message,Toast.LENGTH_LONG).show()
                    }
                }
                is LoginState.DefaultState ->{
                    //DefaultState script
                }
                is LoginState.SendingState ->{
                    //SendingState script
                }
            }

        }

        dialog.show()

    }




    private fun showRegisterWindow() {
        val  dialog = AlertDialog.Builder(this)
        dialog.setTitle("Зарегистрироваться")
        dialog.setMessage("Введите все данные для регистрации")

        val inflater = LayoutInflater.from(this)
        val registerWindow = inflater.inflate(R.layout.register_window, null)
        dialog.setView((registerWindow))

        val email: MaterialEditText = registerWindow.findViewById(R.id.emailField)
        val pass: MaterialEditText = registerWindow.findViewById(R.id.passField)
        val name: MaterialEditText = registerWindow.findViewById(R.id.nameField)
        val phone: MaterialEditText = registerWindow.findViewById(R.id.phoneField)

        dialog.setNegativeButton("Отмена"){
            dialogInterface, which->
            dialogInterface.dismiss()
        }
        dialog.setPositiveButton("Создать"){
            dialogInterface,which->

            authViewModel.register(email = email.text.toString(), pass = pass.text.toString(),
                    name = name.text.toString(), phone = phone.text.toString())
        }

        authViewModel.state.observe(this@MainActivity){ state->
            when(state){
                is RegisterState.SucceededState->{

                }
                is RegisterState.ErrorState<*>->{
                    when(state.message){
                        is Int -> Toast.makeText(dialog.context,getString(state.message),Toast.LENGTH_LONG).show()
                        is String -> Toast.makeText(dialog.context,state.message,Toast.LENGTH_LONG).show()
                    }
                }
                is RegisterState.DefaultState->{
                    //DefaultState script
                }
                is RegisterState.SendingState->{
                    //SendingState script
                }
            }

        }


        dialog.show()
    }






}