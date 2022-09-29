package com.implecode.productsapp.feature_fetch_products.di

import com.implecode.productsapp.feature_fetch_products.data.local.ProductsDao
import com.implecode.productsapp.feature_fetch_products.data.remote.api.ProductsApi
import com.implecode.productsapp.feature_fetch_products.data.repository.ProductsLocalRepositoryImpl
import com.implecode.productsapp.feature_fetch_products.data.repository.ProductsRemoteRepositoryImpl
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsLocalRepository
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Providing Remote Repository
     * */
    @Provides
    @Singleton
    fun providesRemoteRepository(
        api: ProductsApi
    ): ProductsRemoteRepository {
        return ProductsRemoteRepositoryImpl(
            api = api
        )
    }

    /**
     * Providing Local Repository
     * */
    @Provides
    @Singleton
    fun providesLocalRepository(
        dao: ProductsDao
    ): ProductsLocalRepository {
        return ProductsLocalRepositoryImpl(
            dao = dao
        )
    }
}