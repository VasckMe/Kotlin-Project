package com.example.dsw52839.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsw52839.ui.theme.PurpleButton
import com.example.dsw52839.ui.theme.PurpleTextAndIcon
import com.example.dsw52839.utils.Routes
import com.example.dsw52839.viewmodel.LoginViewModel
import com.example.dsw52839.viewmodel.RegisterViewModel

@Composable
fun RegisterPage(navController: NavController, viewModel: RegisterViewModel = viewModel()) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up", color = PurpleTextAndIcon,
            fontSize = 30.sp,
            fontWeight = FontWeight.W700,
        )

        Spacer(modifier = Modifier.height(45.dp))

        SimpleTextField(
            placeholder = "Full Name",
            leadingIcon = Icons.Default.AccountCircle,
            value = viewModel.name.collectAsState().value,
            onValueChange = viewModel::onNameChange,
            isValid = viewModel.isNameValid.collectAsState().value
        )

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(
            placeholder = "Email",
            leadingIcon = Icons.Default.Email,
            value = viewModel.email.collectAsState().value,
            onValueChange = viewModel::onEmailChange,
            isValid = viewModel.isEmailValid.collectAsState().value
        )

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(
            placeholder = "Password",
            leadingIcon = Icons.Default.Lock,
            showTrailingIcon = true,
            value = viewModel.password.collectAsState().value,
            onValueChange = viewModel::onPasswordChange,
            isValid = viewModel.isPasswordValid.collectAsState().value
        )

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(
            placeholder = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            showTrailingIcon = true,
            value = viewModel.confPassword.collectAsState().value,
            onValueChange = viewModel::onConfPasswordChange,
            isValid = viewModel.isConfPasswordValid.collectAsState().value
        )

        Spacer(modifier = Modifier.height(80.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = PurpleButton),
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(route = Routes.loginPage) }
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(140.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?", color = PurpleTextAndIcon,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
            )

            TextButton(
                onClick = { navController.navigate(route = Routes.registerPage) }
            ) {
                Text("Sign In", color = PurpleTextAndIcon,fontWeight = FontWeight.W700, fontSize = 15.sp)
            }
        }
    }
}