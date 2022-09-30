package com.implecode.productsapp.feature_fetch_products.data.repository

import com.implecode.productsapp.feature_fetch_products.data.remote.api.ProductsApi
import com.implecode.productsapp.feature_fetch_products.data.remote.dto.Product
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsRemoteRepository
import retrofit2.Response
import javax.inject.Inject


class ProductsRemoteRepositoryImpl @Inject constructor(
    private val api: ProductsApi
): ProductsRemoteRepository {

    override suspend fun fetchProducts(): Response<List<Product>> {
        return api.fetchAllProducts()
    }
}