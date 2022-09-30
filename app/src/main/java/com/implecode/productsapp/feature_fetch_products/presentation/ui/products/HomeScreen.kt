package com.implecode.productsapp.feature_fetch_products.presentation.ui.products

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.implecode.productsapp.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {
    RequestMultiplePermissions(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

}

@Composable
fun SettingDialog(
    context: Context
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(
                text = context.getString(R.string.permissions_not_granted),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 18.sp
            )
        },
        confirmButton = {
            Button(onClick = {
                openSettingsIntent(
                    context = context
                )
            }) {
                Text(text = context.getString(R.string.settings_dialog_confirm_button_text))
            }
        },
        text = {
            Text(
                text = context.getString(R.string.settings_dialog_text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUi(
    viewModel: ProductsViewModel,
    snackBarHostState: SnackbarHostState,
    context: Context = LocalContext.current,
) {

    val state = viewModel.state.collectAsState().value
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is ProductsScreenEvents.UiEvents.ShowSnackBar -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = event.message.asString(context)
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar()
        },
        containerColor = Color(239,239,245),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            Column{
                HeaderWithActions()
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ){

                    items(state.products){
                        ProductCard(it)
                    }
                }
            }
            if (state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(225,0,102)
                )
            }
        }
    }
}

private fun openSettingsIntent(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    Uri.fromParts("package", context.packageName, null).also {
        intent.data = it
    }
    context.startActivity(intent)

}



@Composable
@Preview
fun HeaderWithActionsPreview() {

}