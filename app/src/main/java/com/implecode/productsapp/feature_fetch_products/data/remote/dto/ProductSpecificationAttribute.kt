package com.implecode.productsapp.feature_fetch_products.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ProductSpecificationAttribute(
    @SerializedName("AllowFiltering")
    val allowFiltering: Boolean,
    @SerializedName("AttributeName")
    val attributeName: String,
    @SerializedName("AttributeTypeName")
    val attributeTypeName: String,
    @SerializedName("DisplayOrder")
    val displayOrder: Int,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("ShowOnProductPage")
    val showOnProductPage: Boolean,
    @SerializedName("ValueRaw")
    val valueRaw: String
)