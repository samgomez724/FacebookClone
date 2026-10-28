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
import me.sgomez.facebookclon.ui.screens.FacebookForgotPasswordScreen
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
                startDestination = Routes.LOGIN,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = Routes.LOGIN) {
                    FacebookLoginScreen(
                        onClickRegister = {
                            myNavController.navigate(route = Routes.REGISTER)
                        },
                        onClickIniciarSesion = {
                            myNavController.navigate(Routes.FEED) {
                                popUpTo(Routes.LOGIN) {
                                    inclusive = true
                                }
                            }
                        },
                        onClickForgotPassword = {
                            myNavController.navigate(Routes.FORGOT_PASSWORD)
                        }
                    )
                }

                composable(Routes.REGISTER) {
                    FacebookRegisterScreen(onClickBack = {
                        myNavController.popBackStack()
                    })
                }

                composable(Routes.FORGOT_PASSWORD) {
                    FacebookForgotPasswordScreen(
                        onClickBack = {
                            myNavController.popBackStack()
                        },
                        onSearchAccount = { _ ->
                            myNavController.popBackStack()
                        }
                    )
                }

                composable(Routes.FEED){
                    FeedScreen (onClickFeed = {
                        myNavController.navigate(Routes.FEED)
                    } )
                }
            }
        }
    }
}
