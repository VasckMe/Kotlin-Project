package com.example.dsw52839

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dsw52839.ui.theme.DSW52839Theme
import com.example.dsw52839.utils.Routes
import com.example.dsw52839.views.LoginPage
import com.example.dsw52839.views.NotePage
import com.example.dsw52839.views.RegisterPage
import com.rashidsaleem.notesapp.home.HomePage

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "login_page",
                builder = {
                    composable(route = Routes.loginPage) {
                        LoginPage(navController = navController)
                    }

                    composable(route = Routes.registerPage) {
                        RegisterPage(navController = navController)
                    }

                    composable(route = Routes.homePage) {
                        HomePage(navigateNext = { route ->
                            navController.navigate(route)
                        })


                    }

                    composable(
                        route = Routes.notePage + "/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                this.type = NavType.IntType
                                this.defaultValue = -1

                            }
                        )
                    ) {
                        NotePage(
                            navigateBack = {

                                navController.popBackStack()

                                Log.d(TAG, "navigateBack: $it")
                            }
                        )
                    }


                }
            )
        }
    }
}