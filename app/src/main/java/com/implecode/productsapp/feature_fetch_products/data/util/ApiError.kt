package com.implecode.productsapp.feature_fetch_products.data.util

import com.implecode.productsapp.R
import com.implecode.productsapp.feature_fetch_products.domain.util.UiText

sealed class ApiError(
    val error: UiText?
) {

    data class ServerError(
        val errorMessage: UiText? = null
    ): ApiError(errorMessage)

    data class ConnectionError(
        val errorMessage: UiText = UiText.StringResource(R.string.api_connection_error)
    ): ApiError(errorMessage)

    data class InternetConnectionError(
        val errorMessage: UiText = UiText.StringResource(R.string.no_internet_connection_error)
    ): ApiError(errorMessage)

    data class UnexpectedError(
        val errorMessage: UiText = UiText.StringResource(R.string.unexpected_error)
    ): ApiError(errorMessage)


}