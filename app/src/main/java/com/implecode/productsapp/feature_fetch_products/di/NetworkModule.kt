package com.implecode.productsapp.feature_fetch_products.di

import com.implecode.productsapp.BuildConfig.BASE_URL
import com.implecode.productsapp.feature_fetch_products.data.remote.api.ProductsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    /**
     * Providing Retrofit Instance
     * */
    @Singleton
    @Provides
    fun providesRetrofitInstance(
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    /**
     * Providing Converter Factory
     * */
    @Singleton
    @Provides
    fun provideConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    /**
     * Providing api
     * */
    @Singleton
    @Provides
    fun providesProductApi(retrofit: Retrofit): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }

}