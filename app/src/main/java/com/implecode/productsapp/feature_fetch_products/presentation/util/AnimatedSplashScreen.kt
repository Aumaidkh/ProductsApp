package com.implecode.productsapp.feature_fetch_products.presentation.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.implecode.productsapp.feature_fetch_products.presentation.ui.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(3500)
        /**
         * Ensuring We don't come on splash screen when back button is pressed
         * */
        navController.navigate(Screen.Home.route) {
            popUpTo(
                Screen.Splash.route
            ) {
                inclusive = true
            }
        }
    }
    SplashScreen(alpha = alphaAnim.value)
}
