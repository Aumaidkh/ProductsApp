package com.implecode.productsapp.feature_fetch_products.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.implecode.productsapp.feature_fetch_products.presentation.ui.theme.ProductsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsAppTheme {
                
                navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                SetUpNavGraph(navController = navController)
            }
        }
    }
}

