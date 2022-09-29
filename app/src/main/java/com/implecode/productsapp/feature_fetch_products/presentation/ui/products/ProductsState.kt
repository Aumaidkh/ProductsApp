package com.implecode.productsapp.feature_fetch_products.presentation.ui.products

import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel

data class ProductsState(
    val isLoading: Boolean = false,
    val products: List<ProductModel> = emptyList()
)
