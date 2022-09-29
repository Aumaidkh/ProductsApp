package com.implecode.productsapp.feature_fetch_products.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ProductAttribute(
    @SerializedName("AllowedFileExtensions")
    val allowedFileExtensions: Any,
    @SerializedName("AttributeControlType")
    val attributeControlType: String,
    @SerializedName("AttributeControlTypeId")
    val attributeControlTypeId: Int,
    @SerializedName("DefaultValue")
    val defaultValue: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("DisplayOrder")
    val displayOrder: Int,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsRequired")
    val isRequired: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("ProductAttributeId")
    val productAttributeId: Int,
    @SerializedName("TextPrompt")
    val textPrompt: String,
    @SerializedName("Values")
    val values: List<Value>
)