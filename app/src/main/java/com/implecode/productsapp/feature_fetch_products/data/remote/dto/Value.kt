package com.implecode.productsapp.feature_fetch_products.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("AllowOutOfStock")
    val allowOutOfStock: Boolean,
    @SerializedName("ColorSquaresRgb")
    val colorSquaresRgb: String,
    @SerializedName("FullSizePictureUrl")
    val fullSizePictureUrl: Any,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsPreSelected")
    val isPreSelected: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PictureUrl")
    val pictureUrl: Any,
    @SerializedName("PriceAdjustment")
    val priceAdjustment: String,
    @SerializedName("PriceAdjustmentValue")
    val priceAdjustmentValue: Double,
    @SerializedName("StockQuanity")
    val stockQuanity: Int
)