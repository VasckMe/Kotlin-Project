package com.example.dsw52839.views

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dsw52839.utils.Routes

@Composable
fun LoginPage(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Sign In")

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(placeholder = "Email or User name", leadingIcon = Icons.Default.AccountCircle)

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(placeholder = "Password",leadingIcon = Icons.Default.Lock, showTrailingIcon = true)

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = { navController.navigate(route = Routes.registerPage) }) {
            Text(text = "Go To Sign Up")
        }
    }
}

@Composable
fun SimpleTextField(placeholder: String, leadingIcon: ImageVector, showTrailingIcon: Boolean = false) {
    val text = remember { mutableStateOf("") }
    val passwordIsHidden = remember { mutableStateOf(showTrailingIcon) }

    TextField(
        value = text.value,
        visualTransformation = if (passwordIsHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { text.value = it },
        placeholder = { Text(placeholder) },
//        background: rgba(71, 26, 160, 1);

    leadingIcon = { Icon(leadingIcon, contentDescription = "Person Icon", tint = Color(red = 71, green = 26, blue = 160, alpha = 255))},
        trailingIcon = {
            if (showTrailingIcon)
                IconButton(onClick = { passwordIsHidden.value = !passwordIsHidden.value }) {
                    Icon(Icons.Default.Close, contentDescription = "Visible Icon")
                }
        },
        singleLine = true,
        isError = text.value.isNotEmpty() && text.value.length < 8,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp))
        )
}
