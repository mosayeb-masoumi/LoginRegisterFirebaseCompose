package com.example.loginregister

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.loginregister.destination.NavigationAppHostRegisterLogin

@Composable
fun RegisterLogin(){
    val navController = rememberNavController()
    NavigationAppHostRegisterLogin(navController = navController)
}


