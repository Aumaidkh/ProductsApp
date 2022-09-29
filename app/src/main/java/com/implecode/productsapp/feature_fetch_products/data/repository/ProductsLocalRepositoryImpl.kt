package com.implecode.productsapp.feature_fetch_products.data.repository

import com.implecode.productsapp.feature_fetch_products.data.local.ProductsDao
import com.implecode.productsapp.feature_fetch_products.data.local.entities.toProductModel
import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel
import com.implecode.productsapp.feature_fetch_products.domain.model.toProductEntity
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsLocalRepository
import javax.inject.Inject

/**
 * Actual Implementation of local repository
 * We will be providing this implementation in module
 * */
class ProductsLocalRepositoryImpl @Inject constructor(
    private val dao: ProductsDao
) : ProductsLocalRepository {

    override suspend fun fetchAllProducts(): List<ProductModel> {
        return dao.getProducts().map { it.toProductModel() }
    }

    override suspend fun insertProducts(products: List<ProductModel>) {
        return dao.insertProducts(
            products = products.map { it.toProductEntity() }
        )
    }

    override suspend fun deleteProducts() {
        return dao.deleteProducts()
    }
}