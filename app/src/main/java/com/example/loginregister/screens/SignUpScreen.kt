package com.example.loginregister.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginregister.components.*
import com.example.loginregister.R
import com.example.loginregister.data.SignUpViewModel
import com.example.loginregister.data.SignUpUIEvent
import com.example.loginregister.destination.DestinationRegisterLogin


@Composable
fun SignUpScreen(navController: NavHostController, signUpViewModel: SignUpViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(28.dp)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextFiled(
                    labelValue = stringResource(id = R.string.first_name),
                    leadingIcon = Icons.Default.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.firstNameError
                )

                MyTextFiled(
                    labelValue = stringResource(id = R.string.last_name),
                    leadingIcon = Icons.Default.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.lastNameError
                )

                MyTextFiled(
                    labelValue = stringResource(id = R.string.email),
                    leadingIcon = Icons.Default.Email,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.emailError
                )

                PasswordTextFiled(
                    labelValue = stringResource(id = R.string.password),
                    leadingIcon = Icons.Default.Lock,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUIState.value.passwordError
                )

                CheckBoxComponent(value = stringResource(R.string.terms_and_conditions),
                    onTextSelected = {
                        navController.navigate(DestinationRegisterLogin.TermsAndConditions.route)
                    },
                    onCheckedChange = {
                        signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckedBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(80.dp))

                ButtonComponent(
                    value = stringResource(R.string.register),
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signUpViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate(DestinationRegisterLogin.LogIn.route)
                })
            }

        }

        if(signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }

        if(signUpViewModel.firebaseRegisteredSuccessfully.value){
            navController.navigate(DestinationRegisterLogin.HomeScreen.route)
            signUpViewModel.firebaseRegisteredSuccessfully.value =false
        }
    }

}