package com.implecode.productsapp.feature_fetch_products.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.implecode.productsapp.feature_fetch_products.data.local.entities.ProductEntity

@Dao
interface ProductsDao {

    /**
     * Deleting a list of products from the database
     * */
    @Query("DELETE FROM products_table")
    suspend fun deleteProducts()

    /**
     * Inserting products inside database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    /**
     * Fetching all products stored inside database
     * */
    @Query("SELECT * FROM products_table")
    suspend fun getProducts(): List<ProductEntity>
}