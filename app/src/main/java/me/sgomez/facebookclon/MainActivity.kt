package me.sgomez.facebookclon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.sgomez.facebookclon.ui.screens.FacebookLoginScreen
import me.sgomez.facebookclon.ui.screens.FacebookRegisterScreen
import me.sgomez.facebookclon.ui.screens.FeedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val myNavController = rememberNavController()


            NavHost(
                navController = myNavController,
                startDestination = "Login",
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = "login") {
                    FacebookLoginScreen(onClickRegister = {
                        myNavController.navigate(route = "register")
                    },
                        onClickIniciarSesion = {
                        myNavController.navigate(route = "feed"){
                            popUpTo(route="login"){
                                inclusive = true
                            }
                        }
                    })
                }

                composable(route = "register") {
                    FacebookRegisterScreen(onClickBack = {
                        myNavController.navigate(route = "login")
                    })
                }

                composable(route= "feed"){
                    FeedScreen (onClickFeed = {
                        myNavController.navigate(route="feed")
                    } )
                }
            }
        }
    }
}