package com.example.loginregister.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loginregister.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {

    val loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

//    private val _loginInProgress = mutableStateOf(false)
//    val loginInProgress: MutableState<Boolean> get() = _loginInProgress
    var loginInProgress = mutableStateOf(false)

    val loginSuccessfully = mutableStateOf(false)


    fun onEvent(event:LoginUIEvent){

        when(event){
            is LoginUIEvent.EmailChanged -> {
               loginUIState.value = loginUIState.value.copy(
                   email = event.email
               )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.RegisterButtonClicked -> {
                login()
            }
        }

        validateDataWithRules()
    }


    private fun validateDataWithRules() {

        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        loginInProgress.value = true

       val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email , password)
            .addOnCompleteListener {
                Log.d("MyTag" ,"sign in =  ${it.isSuccessful}")
                loginInProgress.value = false
                loginSuccessfully.value = true
            }
            .addOnFailureListener {
                Log.d("MyTag" ,"sign in Failed =  ${it.message}")

                loginInProgress.value = false
                loginSuccessfully.value = false
            }
    }
}