package com.example.dsw52839

import HomePage
import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dsw52839.ui.theme.DSW52839Theme
import com.example.dsw52839.utils.Routes
import com.example.dsw52839.utils.StoreDataManager
import com.example.dsw52839.viewmodel.DetailViewModel
import com.example.dsw52839.viewmodel.HomeViewModel
import com.example.dsw52839.views.LoginPage
import com.example.dsw52839.views.NotePage
import com.example.dsw52839.views.RegisterPage

class MainActivity : ComponentActivity() {

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataStoreManager = remember { StoreDataManager(context = this) }
            val navController = rememberNavController()
            val homeViewModel = HomeViewModel()

            val isLogged by dataStoreManager.isLogged.collectAsStateWithLifecycle("")

            NavHost(
                navController = navController,

                startDestination = if (isLogged == true) "home_page" else "login_page",
                builder = {
                    composable(route = Routes.loginPage) {
                        LoginPage(navController = navController, dataStoreManager = dataStoreManager)
                    }

                    composable(route = Routes.registerPage) {
                        RegisterPage(navController = navController)
                    }

                    composable(route = Routes.homePage) {
                        HomePage(navController = navController, viewModel = homeViewModel, dataStoreManager = dataStoreManager)
                    }
                    composable(route = Routes.notePage) {
                        NotePage(
                            navController = navController,
                            viewModel = DetailViewModel(noteID = homeViewModel.chosenNoteId.collectAsState().value
                            )
                        )
                    }
                }
            )
        }
    }
}