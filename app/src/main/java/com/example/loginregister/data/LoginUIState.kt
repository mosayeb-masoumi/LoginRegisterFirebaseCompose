package com.example.loginregister.data

data class LoginUIState(

    var email: String = "",
    var password: String = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false,

)
