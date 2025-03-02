package com.example.dsw52839.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//class LoginPage {
//
//}

@Composable
fun LoginPage() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sign In")
//        TextField(value = "L")

        Button(onClick = { /* TODO */ } ) {
            Text(text = "Click Me!")
        }
    }
}

//fun TextField(value: String) {
//    TextField(value: value)
//}
