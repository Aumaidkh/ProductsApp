package com.implecode.productsapp.feature_fetch_products.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.implecode.productsapp.feature_fetch_products.data.local.entities.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ProductsDatabase: RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

}