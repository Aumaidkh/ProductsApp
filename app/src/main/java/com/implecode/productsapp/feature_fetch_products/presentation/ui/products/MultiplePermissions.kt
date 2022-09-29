package com.implecode.productsapp.feature_fetch_products.presentation.ui.products


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.*
import com.implecode.productsapp.R

@ExperimentalPermissionsApi
@Composable
fun RequestMultiplePermissions(
    permissions: List<String>
) {

    /**
     * Multiple permissions state
     * */
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    /**
     * If any of the permissions are not granted
     * the visibility of default permissions dialog will be
     * toggled on this basis
     * */
    val isPermissionDialogVisible = remember {
        mutableStateOf(true)
    }

    HandleRequests(
        multiplePermissionsState = multiplePermissionsState,
        deniedContent = {
            PermissionDialog(
                title = LocalContext.current.getString(R.string.permission_dialog_title),
                text = LocalContext.current.getString(R.string.permission_dialog_text),
                confirmButtonText = LocalContext.current.getString(R.string.confirm_button_text),
                dialogVisible = isPermissionDialogVisible.value,
                multiplePermissionsState = multiplePermissionsState,

            ){
                multiplePermissionsState.launchMultiplePermissionRequest()
            }
        },
        content = {
            UpdateUi(
                viewModel = hiltViewModel(),
                snackBarHostState = remember {
                    SnackbarHostState()
                }
            )
        }
    )
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionDialog(
    title: String,
    text: String,
    confirmButtonText: String,
    dialogVisible: Boolean,
    multiplePermissionsState: MultiplePermissionsState,
    onRequestPermission: () -> Unit,

) {
    multiplePermissionsState.permissions.any { permissionState ->
      permissionState.status.shouldShowRationale
    }.also {
        if (it){
            SettingDialog(
                context = LocalContext.current
            )
        }
    }
    

    if (dialogVisible){
        AlertDialog(
            onDismissRequest = {  },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            confirmButton = {
                Button(onClick = {
                    onRequestPermission.invoke()
                }) {
                    Text(text = confirmButtonText)
                }
            }
        )
    } else {
        SettingDialog(context = LocalContext.current)
    }
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequests(
    multiplePermissionsState: MultiplePermissionsState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    var shouldShowRationale by remember { mutableStateOf(false) }
    val result = multiplePermissionsState.permissions.all {
        shouldShowRationale = it.status.shouldShowRationale
        it.status == PermissionStatus.Granted
    }
    if (result) {
        content()
    } else {
        deniedContent(shouldShowRationale)
    }
}
