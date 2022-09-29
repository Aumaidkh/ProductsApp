package com.implecode.productsapp.feature_fetch_products.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.implecode.productsapp.feature_fetch_products.data.use_case.FetchProductsUseCaseImpl
import com.implecode.productsapp.feature_fetch_products.data.util.ConnectionCheckerImpl
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsLocalRepository
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsRemoteRepository
import com.implecode.productsapp.feature_fetch_products.domain.use_case.FetchProductsUseCase
import com.implecode.productsapp.feature_fetch_products.domain.util.ConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Providing Connection Manager
     * */
    @Provides
    @Singleton
    fun providesConnectivityManager(
        app: Application
    ): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /**
     * Providing Connection Checker
     * */
    @Provides
    @Singleton
    fun providesConnectionChecker(
        connectivityManager: ConnectivityManager
    ): ConnectionChecker {
        // Returning actual implementation here
        return ConnectionCheckerImpl(connectivityManager)
    }

    /**
     * Providing Fetch Products Use Case
     * */
    @Provides
    @Singleton
    fun providesFetchProductsUseCase(
        remoteRepository: ProductsRemoteRepository,
        localRepository: ProductsLocalRepository,
        connectionChecker: ConnectionChecker
    ): FetchProductsUseCase {
        return FetchProductsUseCaseImpl(
            remote = remoteRepository,
            local = localRepository,
            connectionChecker = connectionChecker
        )
    }
}