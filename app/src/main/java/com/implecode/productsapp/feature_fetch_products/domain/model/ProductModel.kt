package com.implecode.productsapp.feature_fetch_products.domain.model

import com.implecode.productsapp.feature_fetch_products.data.local.entities.ProductEntity

data class ProductModel(
    val id: Int,
    val isVeg: Boolean,
    val weight: String,
    val imageUrl: String,
    val title: String,
    val prevPrice: Float,
    val newPrice: Float,
    val deliveryType: String,
    val deliveryTiming: String
)

fun ProductModel.toProductEntity() =
    ProductEntity(
        id,
        isVeg,
        weight,
        imageUrl,
        title,
        prevPrice,
        newPrice,
        deliveryType,
        deliveryTiming
    )
