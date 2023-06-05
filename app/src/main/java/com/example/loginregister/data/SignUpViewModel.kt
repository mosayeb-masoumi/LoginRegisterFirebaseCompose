package com.example.loginregister.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.loginregister.data.rules.Validator
import com.example.loginregister.destination.DestinationRegisterLogin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignUpViewModel : ViewModel() {


    var registrationUIState = mutableStateOf(RegisterationUIState())

    var allValidationsPassed = mutableStateOf(false)


    private val _signUpInProgress = mutableStateOf(false)
    val signUpInProgress:MutableState<Boolean> get() = _signUpInProgress


    private val _firebaseRegisteredSuccessfully = mutableStateOf(false)// using in destination to go home after signed in successfully
    val firebaseRegisteredSuccessfully: MutableState<Boolean> get() = _firebaseRegisteredSuccessfully




//    private val _signOutInProgress = MutableStateFlow(false)
//    val signOutInProgress: StateFlow<Boolean> get() = _signOutInProgress
    private val _signOutInProgress = mutableStateOf(false)
    val signOutInProgress:MutableState<Boolean> get() = _signOutInProgress


//    private val _firebaseSignOutSuccessfully = MutableStateFlow(false)
//    val firebaseSignOutSuccessfully: StateFlow<Boolean> get() = _firebaseSignOutSuccessfully

    private val _firebaseSignOutSuccessfully = mutableStateOf(false)// using in destination to go home after signed in successfully
    val firebaseSignOutSuccessfully: MutableState<Boolean> get() = _firebaseSignOutSuccessfully



    fun onEvent(event: SignUpUIEvent) {


        when (event) {

            is SignUpUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is SignUpUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is SignUpUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is SignUpUIEvent.PrivacyPolicyCheckedBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is SignUpUIEvent.RegisterButtonClicked -> {

                var firstname = registrationUIState.value.firstName
                var lastname = registrationUIState.value.lastName
                var email = registrationUIState.value.email
                var password = registrationUIState.value.password
                signUp()

            }
        }

        validateDataWithRules()
    }

    private fun signUp() {

        createUserInFirebase(
            email = registrationUIState.value.email,
            registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {

        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )
        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value =
            fNameResult.status && lNameResult.status && emailResult.status &&
                    passwordResult.status && privacyPolicyResult.status
    }


    private fun createUserInFirebase(email: String, password: String) {

//        signUpInProgress.value = truehhhhhh
        _signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d("MyTag", "isSuccessful = ${it.isSuccessful}")
//                signUpInProgress.value = false
                _signUpInProgress.value = false
//                firebaseRegisteredSuccessfully.value = true
                _firebaseRegisteredSuccessfully.value = true

            }
            .addOnFailureListener {
                Log.d("MyTag", "Exception== ${it.message}")
//                signUpInProgress.value = false
                _signUpInProgress.value = false
                _firebaseRegisteredSuccessfully.value  = false
            }
    }

    fun performNavigation(navController: NavHostController) {
        // Perform your logic and trigger navigation using the NavController
        navController.navigate(DestinationRegisterLogin.HomeScreen.route)
    }

    fun logOut(){

        _signOutInProgress.value = true

        val firebaseAuth =FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
                 Log.d("MyTag" , "signOut Succeed")
                _signOutInProgress.value = false
                _firebaseSignOutSuccessfully.value = true
            }else{
                Log.d("MyTag" , "signOut Failed")
                _signOutInProgress.value = false
                _firebaseSignOutSuccessfully.value = false
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }
}