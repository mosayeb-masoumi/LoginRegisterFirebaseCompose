package com.example.loginregister.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginregister.R
import com.example.loginregister.components.ButtonComponent
import com.example.loginregister.components.HeadingTextComponent
import com.example.loginregister.data.SignUpViewModel
import com.example.loginregister.destination.DestinationRegisterLogin

@Composable
fun HomeScreen(navController: NavHostController, signUpViewModel: SignUpViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(all = 20.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HeadingTextComponent(value = stringResource(R.string.home_screen))

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(
                    value = stringResource(R.string.sign_out), onButtonClicked = {
                        signUpViewModel.logOut()
                    },
                    isEnabled = true
                )
            }
        }

        if(signUpViewModel.signOutInProgress.value){
            CircularProgressIndicator()
        }

        if(signUpViewModel.firebaseSignOutSuccessfully.value){
            navController.navigate(DestinationRegisterLogin.LogIn.route)
            signUpViewModel.firebaseSignOutSuccessfully.value = false
        }
    }

}