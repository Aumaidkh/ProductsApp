package com.implecode.productsapp.feature_fetch_products.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ProductFullSizePicture(
    @SerializedName("DisplayOrder")
    val displayOrder: Int,
    @SerializedName("PictureUrl")
    val pictureUrl: String
)