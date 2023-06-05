package com.example.loginregister.data

sealed class SignUpUIEvent {
    data class FirstNameChanged(val firstName: String) : SignUpUIEvent()
    data class LastNameChanged(val lastName: String) : SignUpUIEvent()
    data class EmailChanged(val email: String) : SignUpUIEvent()
    data class PasswordChanged(val password: String) : SignUpUIEvent()
    data class PrivacyPolicyCheckedBoxClicked(val status: Boolean) : SignUpUIEvent()

    object RegisterButtonClicked: SignUpUIEvent()
}
