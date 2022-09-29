package com.implecode.productsapp.feature_fetch_products.domain.use_case

import com.implecode.productsapp.feature_fetch_products.data.util.Result
import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface FetchProductsUseCase {

    fun execute(): Flow<Result<List<ProductModel>>>
}