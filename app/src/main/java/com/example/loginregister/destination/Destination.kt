package com.example.loginregister.destination

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginregister.screens.HomeScreen
import com.example.loginregister.screens.LogInScreen
import com.example.loginregister.screens.SignUpScreen
import com.example.loginregister.screens.TermsAndConditionScreen


sealed class DestinationRegisterLogin(var route: String ){

    object SignUp: DestinationRegisterLogin("sign_up")
    object TermsAndConditions: DestinationRegisterLogin("terms_and_conditions")
    object LogIn: DestinationRegisterLogin("log_in")
    object HomeScreen: DestinationRegisterLogin("home_screen")

}

@Composable
fun NavigationAppHostRegisterLogin(navController: NavHostController) {

    NavHost(navController, startDestination = DestinationRegisterLogin.LogIn.route) {

        composable(DestinationRegisterLogin.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(DestinationRegisterLogin.TermsAndConditions.route){
            TermsAndConditionScreen()
        }

        composable(DestinationRegisterLogin.LogIn.route){
            LogInScreen(navController)
        }

        composable(DestinationRegisterLogin.HomeScreen.route){
            HomeScreen(navController)
        }

    }
}