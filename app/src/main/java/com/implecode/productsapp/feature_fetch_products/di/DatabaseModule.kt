package com.implecode.productsapp.feature_fetch_products.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.implecode.productsapp.feature_fetch_products.data.local.ProductsDao
import com.implecode.productsapp.feature_fetch_products.data.local.ProductsDatabase
import com.implecode.productsapp.feature_fetch_products.data.util.GsonParser
import com.implecode.productsapp.feature_fetch_products.data.util.JsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



    /**
     * Providing products dao
     * */
    @Provides
    @Singleton
    fun providesProductsDao(
        database: ProductsDatabase
    ): ProductsDao {
        return database.getProductsDao()
    }

    /**
     * Providing database instance
     * */
    @Provides
    @Singleton
    fun providesProductsDatabase(
        app: Application
    ): ProductsDatabase {
        return Room.databaseBuilder(
            app,
            ProductsDatabase::class.java,
            "products_db"
        )
            .build()
    }

    /**
     * Providing Json Parser
     * We will be using gson parser here, however if in future we wish to
     * use some other parser we can provide that here
     * */
    @Singleton
    @Provides
    fun providesJsonParser(): JsonParser {
        return GsonParser(Gson())
    }


}