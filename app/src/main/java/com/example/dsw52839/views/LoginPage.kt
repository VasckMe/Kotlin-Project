package com.example.dsw52839.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
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
import com.example.dsw52839.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Composable
fun LoginPage(navController: NavController, viewModel: LoginViewModel = viewModel()) {
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
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Sign In", color = PurpleTextAndIcon,
            fontSize = 30.sp,
            fontWeight = FontWeight.W700,
        )

        Spacer(modifier = Modifier.height(40.dp))

        SimpleTextField(
            placeholder = "Email or Username",
            leadingIcon = Icons.Default.AccountCircle,
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

        TextButton(
            modifier = Modifier.align(alignment = Alignment.End),
            onClick = { navController.navigate(route = Routes.registerPage) }
        ) {
            Text("Forget password", color = PurpleTextAndIcon,fontWeight = FontWeight.W700, fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = PurpleButton),
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(route = Routes.registerPage) }
        ) {
            Text(text = "Sign In")
        }
    }
}

@Composable
fun SimpleTextField(
    placeholder: String,
    leadingIcon: ImageVector,
    showTrailingIcon: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean
) {
    val passwordIsHidden = remember { mutableStateOf(showTrailingIcon) }

    TextField(
        value = value,
        visualTransformation = if (passwordIsHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (showTrailingIcon) KeyboardType.Password else KeyboardType.Text),
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        leadingIcon = { Icon(leadingIcon, contentDescription = null, tint = PurpleTextAndIcon) },
        trailingIcon = {
            if (showTrailingIcon) {
                IconButton(onClick = { passwordIsHidden.value = !passwordIsHidden.value }) {
                    Icon(Icons.Default.Close, contentDescription = null)
                }
            }
        },
        singleLine = true,
        isError = !isValid,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .border(2.dp, PurpleBorder, RoundedCornerShape(15.dp))
    )
}
