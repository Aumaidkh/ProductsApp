package com.implecode.productsapp.feature_fetch_products.domain.repository

import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel

interface ProductsLocalRepository {

    suspend fun fetchAllProducts(): List<ProductModel>

    suspend fun insertProducts(products: List<ProductModel>)

    suspend fun deleteProducts()
}