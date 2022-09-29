package com.implecode.productsapp.feature_fetch_products.presentation.util

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

const val PERMISSIONS_REQUEST_CODE = 1001

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isDeniedPermanently(): Boolean{
    return !this.status.isGranted && !this.status.shouldShowRationale
}