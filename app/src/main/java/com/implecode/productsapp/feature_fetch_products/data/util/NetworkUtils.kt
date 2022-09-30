package com.implecode.productsapp.feature_fetch_products.data.util

import com.implecode.productsapp.feature_fetch_products.domain.util.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object NetworkUtils {

    /**
     * Handling the api errors here
     * Function check for internet connectivity an shows the corresponding error message
     * */
    suspend fun <T : Any> makeApiRequest(
        isConnectedToInternet: Boolean,
        onRequest: suspend () -> Response<T>
    ): ResponseWrapper<T> =
        withContext(Dispatchers.IO) {
            /**
             * Internet Not Detected, don't make the api call*/
            if (!isConnectedToInternet) {
                return@withContext ResponseWrapper.Error(ApiError.InternetConnectionError())
            }
            safeApiCall {
                with(onRequest()) {
                    when {
                        /**
                         * Api Call Succeeded
                         * */
                        isSuccessful && body() != null -> {
                            ResponseWrapper.Success(body() as T)
                        }
                        /**
                         * Server Error Happened, will be showing a message
                         * directly coming from the server
                         * */
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