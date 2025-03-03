package com.example.dsw52839.views

import android.graphics.Paint.Align
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsw52839.R
import com.example.dsw52839.ui.theme.PurpleBorder
import com.example.dsw52839.ui.theme.PurpleButton
import com.example.dsw52839.ui.theme.PurpleTextAndIcon
import com.example.dsw52839.utils.Routes

class LoginViewModel: ViewModel() {
    val text = ""
}

@Composable
fun LoginPage(navController: NavController, viewModel: LoginViewModel = LoginViewModel()) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(130.dp),
            painter = painterResource(id =  R.drawable.logo),
            contentDescription = "App Logo"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Sign In", color = PurpleTextAndIcon,
            fontWeight = FontWeight.W700,
            fontSize = 30.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(placeholder = "Email or User name", leadingIcon = Icons.Default.AccountCircle)

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(placeholder = "Password",leadingIcon = Icons.Default.Lock, showTrailingIcon = true)

        Spacer(modifier = Modifier.height(40.dp))

        TextButton(
            modifier = Modifier
                .align(alignment = Alignment.End),
            onClick = { navController.navigate(route = Routes.registerPage) }

        ) {
            Text(
                "Forget password",
                color = PurpleTextAndIcon,
                fontWeight = FontWeight.W700,
                fontSize = 15.sp,
                letterSpacing = 0.sp,
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = PurpleButton),
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(route = Routes.registerPage) }
        )
        {
            Text(text = "Sign In")
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

    leadingIcon = { Icon(leadingIcon, contentDescription = "Person Icon", tint = PurpleTextAndIcon)},
        trailingIcon = {
            if (showTrailingIcon)
                IconButton(onClick = { passwordIsHidden.value = !passwordIsHidden.value }) {
                    Icon(Icons.Default.Close, contentDescription = "Visible Icon")
                }
        },
        singleLine = true,
        isError = text.value.isNotEmpty() && text.value.length < 8,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, PurpleBorder, RoundedCornerShape(15.dp))
        )
}
