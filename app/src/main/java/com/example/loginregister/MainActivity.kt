package com.example.loginregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.loginregister.ui.theme.LoginRegisterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginRegisterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    RegisterLogin()

                    //sources
//                    https://www.youtube.com/watch?v=PeUERQJnHdI
//                    https://www.youtube.com/watch?v=n9IrkANVGxU
//                    https://www.youtube.com/watch?v=peSfaIhKgfw
//                    https://www.youtube.com/watch?v=008f7IUVYDQ
 //                   https://www.youtube.com/watch?v=KOI7fS7k8Y0&t=1s   // setup firebase
                }
            }
        }
    }
}

