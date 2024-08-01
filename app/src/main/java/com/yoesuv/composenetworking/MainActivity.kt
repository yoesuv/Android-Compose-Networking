package com.yoesuv.composenetworking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yoesuv.composenetworking.ui.route.AppRoute
import com.yoesuv.composenetworking.ui.screens.GalleryPlaceScreen
import com.yoesuv.composenetworking.ui.screens.HomeScreen
import com.yoesuv.composenetworking.ui.screens.ListPlaceScreen
import com.yoesuv.composenetworking.ui.theme.ComposeNetworkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNetworkingTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = AppRoute.Home) {
                    composable<AppRoute.Home> {
                        HomeScreen(navController)
                    }
                    composable<AppRoute.ListPlace> {
                        ListPlaceScreen()
                    }
                    composable<AppRoute.Gallery> {
                        GalleryPlaceScreen()
                    }
                }

            }
        }
    }
}