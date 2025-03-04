package com.example.dsw52839.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsw52839.R
import com.example.dsw52839.ui.theme.PurpleButton
import com.example.dsw52839.ui.theme.PurpleTextAndIcon
import com.example.dsw52839.utils.Routes
import com.example.dsw52839.viewmodel.RegisterViewModel

@Composable
fun RegisterPage(navController: NavController, viewModel: RegisterViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PurpleTextAndIcon)
            }

            Spacer(modifier = Modifier.height(100.dp))

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
                modifier = Modifier.fillMaxWidth().height(50.dp),
                onClick = { navController.navigate(route = Routes.loginPage) }
            ) {
                Text(text = "Sign Up", fontWeight = FontWeight.W600, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

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
                    onClick = { navController.navigate(route = Routes.loginPage) }
                ) {
                    Text("Sign In", color = PurpleTextAndIcon, fontWeight = FontWeight.W700, fontSize = 15.sp)
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ellipse),
            contentDescription = "Ellipse Decoration",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .padding(top = 20.dp)
        )
    }
}
