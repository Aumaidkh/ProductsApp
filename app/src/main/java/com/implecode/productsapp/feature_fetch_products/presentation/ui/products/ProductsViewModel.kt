package com.implecode.productsapp.feature_fetch_products.presentation.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.implecode.productsapp.feature_fetch_products.data.util.Result
import com.implecode.productsapp.feature_fetch_products.domain.use_case.FetchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase
): ViewModel() {

    /**
     * State
     * */
    private val _state = MutableStateFlow(ProductsState())
    val state = _state.asStateFlow()

    /**
     * Events Flow
     * */
    private val _eventsChannel = Channel<ProductsScreenEvents.UiEvents>()
    val eventFlow = _eventsChannel.receiveAsFlow()

    /**
     * */

    init {
        fetchProducts()
    }

    private fun fetchProducts(){
        fetchProductsUseCase.execute().onEach { productsResult ->
            when(productsResult){
                is Result.Loading -> {
                    /**
                     * Updating the state
                     * */
                    _state.update {
                        it.copy(
                            isLoading = true,
                            products = productsResult.data ?: emptyList()
                        )
                    }
                }

                is Result.Success -> {
                    /**
                     * Updating the state
                     * */
                    _state.update {
                        it.copy(
                            isLoading = false,
                            products = productsResult.value
                        )
                    }
                }

                is Result.Error -> {
                    /**
                     * Updating the state
                     * */
                    _state.update {
                        it.copy(
                            isLoading = false,
                            products = productsResult.data ?: emptyList()
                        )
                    }

                    /**
                     * Sending Error as snackbar message
                     * */
                    _eventsChannel.send(
                        ProductsScreenEvents.UiEvents.ShowSnackBar(
                            message = productsResult.error!!
                        )
                    )
                }

            }
        }.launchIn(viewModelScope)
    }
}