package com.example.loginregister.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginregister.components.*
import com.example.loginregister.R
import com.example.loginregister.data.LoginUIEvent
import com.example.loginregister.data.LoginViewModel
import com.example.loginregister.destination.DestinationRegisterLogin


@Composable
fun LogInScreen(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(28.dp)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(value = stringResource(R.string.login))
                NormalTextComponent(value = stringResource(R.string.welcome_back))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextFiled(labelValue = stringResource(R.string.email),
                    leadingIcon = Icons.Default.Message,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError

                )
                PasswordTextFiled(labelValue = stringResource(R.string.password),
                    leadingIcon = Icons.Default.Lock,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(40.dp))
                UnderLineTextComponent(value = stringResource(R.string.forget_password))
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate(DestinationRegisterLogin.SignUp.route)
                })

            }
        }

        if(loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }


        if(loginViewModel.loginSuccessfully.value){
            navController.navigate(DestinationRegisterLogin.HomeScreen.route)
            loginViewModel.loginSuccessfully.value = false
        }
    }

}