package com.implecode.productsapp.feature_fetch_products.domain.repository

import com.implecode.productsapp.feature_fetch_products.data.remote.dto.Product
import retrofit2.Response


interface ProductsRemoteRepository {

    /**
     * Fetches list of products from the api
     * */
    suspend fun fetchProducts(): Response<List<Product>>


}