package com.implecode.productsapp.feature_fetch_products.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel

@Entity(
    tableName = "products_table"
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
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

fun ProductEntity.toProductModel() =
    ProductModel(
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
