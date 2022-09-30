package com.implecode.productsapp.feature_fetch_products.domain.model

import com.implecode.productsapp.feature_fetch_products.data.local.entities.ProductEntity

/**
 * The actual model we will be using in domain
 * and presentation layer*/
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
        id = id,
        isVeg = isVeg,
        weight = weight,
        imageUrl = imageUrl,
        title = title,
        prevPrice = prevPrice,
        newPrice = newPrice,
        deliveryType = deliveryType,
        deliveryTiming = deliveryTiming
    )
