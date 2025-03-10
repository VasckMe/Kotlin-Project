package com.example.dsw52839.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsw52839.R
import com.example.dsw52839.model.SimpleTextField
import com.example.dsw52839.ui.theme.PurpleButton
import com.example.dsw52839.ui.theme.PurpleTextAndIcon
import com.example.dsw52839.utils.Routes
import com.example.dsw52839.utils.StoreDataManager
import com.example.dsw52839.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    navController: NavController,
    dataStoreManager: StoreDataManager,
    viewModel: LoginViewModel = viewModel()
) {

    val scope = rememberCoroutineScope()

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

        Spacer(modifier = Modifier.height(45.dp))

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

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            modifier = Modifier.align(alignment = Alignment.End),
            onClick = {  }
        ) {
            Text("Forget password ?", color = PurpleTextAndIcon,fontWeight = FontWeight.W700, fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = PurpleButton),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            onClick = {
                viewModel.checkCreds()

                if (viewModel.isEmailValid.value && viewModel.isPasswordValid.value) {
                    scope.launch {
                        dataStoreManager.saveData(isLogged = true)
                    }

                    navController.navigate(route = Routes.homePage)
                }

            }
        ) {
            Text(text = "Sign In")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have account?", color = PurpleTextAndIcon,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
            )

            TextButton(
                onClick = { navController.navigate(route = Routes.registerPage) }
            ) {
                Text("Sign Up", color = PurpleTextAndIcon, fontWeight = FontWeight.W700, fontSize = 15.sp)
            }
        }
    }
}
