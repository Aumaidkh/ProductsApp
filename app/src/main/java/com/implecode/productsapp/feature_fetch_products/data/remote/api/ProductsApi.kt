package com.implecode.productsapp.feature_fetch_products.data.remote.api

import com.implecode.productsapp.BuildConfig.PRODUCTS_END_POINT
import com.implecode.productsapp.feature_fetch_products.data.remote.dto.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    /**
     * Fetches list of products from the server
     * */
    @GET(PRODUCTS_END_POINT)
    suspend fun fetchAllProducts(): Response<List<Product>>

}