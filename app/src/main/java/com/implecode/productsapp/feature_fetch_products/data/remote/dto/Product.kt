package com.implecode.productsapp.feature_fetch_products.data.remote.dto


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.implecode.productsapp.feature_fetch_products.data.local.entities.ProductEntity
import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel

data class Product(
    @SerializedName("CustomerEntersPrice")
    val customerEntersPrice: Boolean,
    @SerializedName("DiscountAmount")
    val discountAmount: Double,
    @SerializedName("DiscountPercentage")
    val discountPercentage: Double,
    @SerializedName("DisplayOrder")
    val displayOrder: Int,
    @SerializedName("DisplayStockAvailability")
    val displayStockAvailability: Boolean,
    @SerializedName("DisplayStockQuantity")
    val displayStockQuantity: Boolean,
    @SerializedName("FullDescription")
    val fullDescription: String,
    @SerializedName("Gtin")
    val gtin: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsGiftCard")
    val isGiftCard: Boolean,
    @SerializedName("ManageInventoryMethodId")
    val manageInventoryMethodId: Int,
    @SerializedName("MaximumCustomerEnteredPrice")
    val maximumCustomerEnteredPrice: Double,
    @SerializedName("MinimumCustomerEnteredPrice")
    val minimumCustomerEnteredPrice: Double,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Price")
    val price: Double,
    @SerializedName("ProductAttributes")
    val productAttributes: List<ProductAttribute>,
    @SerializedName("ProductCategories")
    val productCategories: String,
    @SerializedName("ProductFullSizePictures")
    val productFullSizePictures: List<ProductFullSizePicture>,
    @SerializedName("ProductPictures")
    val productPictures: List<ProductPicture>,
    @SerializedName("ProductSpecificationAttributes")
    val productSpecificationAttributes: List<ProductSpecificationAttribute>,
    @SerializedName("Published")
    val published: Boolean,
    @SerializedName("ShortDescription")
    val shortDescription: String,
    @SerializedName("ShowOnKiosk")
    val showOnKiosk: Boolean,
    @SerializedName("ShowOnPosMobile")
    val showOnPosMobile: Boolean,
    @SerializedName("ShowOnPosWeb")
    val showOnPosWeb: Boolean,
    @SerializedName("ShowOnWebsite")
    val showOnWebsite: Boolean,
    @SerializedName("Sku")
    val sku: String,
    @SerializedName("StockQuantity")
    val stockQuantity: Int,
    @SerializedName("TaxRate")
    val taxRate: Double,
    @SerializedName("UsePercentage")
    val usePercentage: Boolean
)

fun Product.toProductEntity() =
    ProductEntity(
        id = id,
        prevPrice = discountAmount.toFloat(),
        newPrice = price.toFloat(),
        title = name,
        imageUrl = productFullSizePictures[0].pictureUrl,
        isVeg = true,
        weight = "1.kg",
        deliveryType = "Standard ",
        deliveryTiming = "( Tomorrow Afternoon )"

    )

fun Product.toProductModel() =
    ProductModel(
        id = id,
        prevPrice = price.toFloat(),
        newPrice = price.toFloat() - discountAmount.toFloat(),
        title = name,
        imageUrl = if (productPictures.isNotEmpty()) productPictures[0].pictureUrl else "https://images.pexels.com/photos/4046710/pexels-photo-4046710.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        isVeg = true,
        weight = "1.kg",
        deliveryType = "Standard ",
        deliveryTiming = "( Tomorrow Afternoon )"
    )