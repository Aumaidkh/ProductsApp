package com.implecode.productsapp.feature_fetch_products.domain.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Wrapper class for getting access to string resources inside viewModel
 * without context
 * */
sealed class UiText {

    /**
     * A dynamic string wrapper*/
    data class DynamicString(
        val value: String
    ) : UiText()

    /**
     * Static string wrapper,
     * contains string resource id instead of the actual string
     * */
    data class StringResource(
        @StringRes val resId: Int
    ) : UiText()


    /**
     * Mapping the UiString into corresponding strings
     * */
    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId)
        }
    }

    /**
     * Mapping the UiString into corresponding strings
     * */
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId)
        }
    }
}
