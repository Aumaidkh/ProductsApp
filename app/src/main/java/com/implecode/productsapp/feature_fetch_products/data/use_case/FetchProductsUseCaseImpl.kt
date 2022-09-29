package com.implecode.productsapp.feature_fetch_products.data.use_case

import com.implecode.productsapp.feature_fetch_products.data.remote.dto.toProductModel
import com.implecode.productsapp.feature_fetch_products.data.util.NetworkUtils.makeApiRequest
import com.implecode.productsapp.feature_fetch_products.data.util.Result
import com.implecode.productsapp.feature_fetch_products.data.util.ResponseWrapper
import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsLocalRepository
import com.implecode.productsapp.feature_fetch_products.domain.repository.ProductsRemoteRepository
import com.implecode.productsapp.feature_fetch_products.domain.use_case.FetchProductsUseCase
import com.implecode.productsapp.feature_fetch_products.domain.util.ConnectionChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchProductsUseCaseImpl @Inject constructor(
    private val remote: ProductsRemoteRepository,
    private val local: ProductsLocalRepository,
    private val connectionChecker: ConnectionChecker
): FetchProductsUseCase {

    override fun execute(): Flow<Result<List<ProductModel>>> = flow {
        /**
         * Emit Loading State First
         * */
        emit(Result.Loading())

        /**
         * Hit Local Cache and emit the data
         * */
        val productsInCache = local.fetchAllProducts().also {
            emit(
                Result.Loading(
                    data = it
                )
            )
        }

        /**
         * Make Api Call And Update the Local Cache Here
         * */
        makeApiRequest(
            isConnectedToInternet = connectionChecker.isConnected() // Will be provided by connection manager
        ) {
            remote.fetchProducts()
        }.also { apiResult ->
            when (apiResult) {
                /*
                * Api call succeeded without any errors
                * */
                is ResponseWrapper.Success -> {
                    /*
                    * Re populate the cache
                    * */
                    local.apply {
                        /*
                        * Clear the local db*/
                        deleteProducts()
                        /*
                        * Repopulate the local db
                        * */
                        insertProducts(apiResult.data.map { it.toProductModel() })

                        /*
                        * Emit the new items from the db
                        * */
                        emit(Result.Success(fetchAllProducts()))
                    }
                }

                /*
                * Something went wrong with api call
                * */
                is ResponseWrapper.Error -> {
                    /*
                    * Emit the data from the local db along with error message
                    * */
                    emit(
                        Result.Error(
                            data = productsInCache,
                            error = apiResult.error.error
                        )
                    )
                }
            }
        }
    }

}