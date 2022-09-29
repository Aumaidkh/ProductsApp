package com.implecode.productsapp.feature_fetch_products.domain.util

/**
 * Abstracting the actual implementation out*/
interface ConnectionChecker {

    fun isConnected(): Boolean
}