package com.implecode.productsapp.feature_fetch_products.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.implecode.productsapp.feature_fetch_products.presentation.ui.products.HomeScreen
import com.implecode.productsapp.feature_fetch_products.presentation.util.AnimatedSplashScreen
import com.implecode.productsapp.feature_fetch_products.presentation.util.Screen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(
            route = Screen.Splash.route
        ) {
            AnimatedSplashScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }
    }
}