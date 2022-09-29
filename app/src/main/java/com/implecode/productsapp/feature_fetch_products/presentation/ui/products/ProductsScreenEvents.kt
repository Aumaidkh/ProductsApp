package com.implecode.productsapp.feature_fetch_products.presentation.ui.products

import com.implecode.productsapp.feature_fetch_products.domain.util.UiText

sealed class ProductsScreenEvents {

    sealed class UiEvents{
        data class ShowSnackBar(
            val message: UiText
        ): UiEvents()
    }
}