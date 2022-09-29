package com.implecode.productsapp.feature_fetch_products.data.util

import com.implecode.productsapp.feature_fetch_products.domain.util.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object NetworkUtils {

    suspend fun <T : Any> makeApiRequest(
        isConnectedToInternet: Boolean,
        onRequest: suspend () -> Response<T>
    ): ResponseWrapper<T> =
        withContext(Dispatchers.IO) {
            if (!isConnectedToInternet) {
                return@withContext ResponseWrapper.Error(ApiError.InternetConnectionError())
            }
            safeApiCall {
                with(onRequest()) {
                    when {
                        isSuccessful && body() != null -> {
                            ResponseWrapper.Success(body() as T)
                        }
                        else -> {
                            ResponseWrapper.Error(
                                ApiError.ServerError(
                                    errorMessage = UiText.DynamicString(message())
                                )
                            )
                        }
                    }
                }
            }
        }


    private suspend fun <T : Any> safeApiCall(call: suspend () -> ResponseWrapper<T>): ResponseWrapper<T> {
        return try {
            call()
        } catch (e: Exception) {
            ResponseWrapper.Error(ApiError.UnexpectedError())
        }
    }
}