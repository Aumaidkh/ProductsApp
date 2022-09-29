package com.implecode.productsapp.feature_fetch_products.presentation.ui.products

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.implecode.productsapp.R
import com.implecode.productsapp.feature_fetch_products.domain.model.ProductModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductCard(
    product: ProductModel
) {

    /**
     * Toggling visibility of quantity buttons
     * */
    val quantityVisibility = rememberSaveable{
        mutableStateOf(false)
    }

    /**
     * Storing state of quantity
     * */
    val quantityState = rememberSaveable {
        mutableStateOf(1)
    }

    /**
     * Loading Image*/
    val painter = rememberImagePainter(
        data = product.imageUrl,
        builder = {

        }
    )

    Surface(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * Type and Quantity
             * */
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(15.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (product.isVeg) Color.Green else Color.Red
                    ),
                ) {
                    /*
                    * Veg/Non-Veg*/
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxSize()
                            .aspectRatio(1f)
                            .background(
                                color = if (product.isVeg) Color.Green else Color.Red,
                                shape = CircleShape
                            )
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                /*
                * Quantity
                * */
                Text(
                    text = product.weight,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            /**
             * Product Image
             * */
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(120.dp),
                painter = painter,
                contentDescription = "pizza"
            )

            Spacer(modifier = Modifier.height(4.dp))

            /**
             * Product Title*/
            Text(
                modifier = Modifier.width(200.dp),
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            /**
             * Price Row*/
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rs",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = product.prevPrice.toString(),
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = product.newPrice.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.height(4.dp))

            if (quantityVisibility.value) {
                QuantityComponent(
                    quantity = quantityState.value,
                    onDecreaseQuantity = {
                        if (quantityState.value>=2) quantityState.value--
                    },
                    onIncreaseQuantity = {
                        quantityState.value++
                    }
                )
            } else {
                AddButton {
                    quantityVisibility.value = !quantityVisibility.value
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            /*Delivery Type*/
            Text(
                text = product.deliveryType,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            /*Delivery Timing*/
            Text(
                text = product.deliveryTiming,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun QuantityComponent(
    quantity: Int,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit
) {
    Row(
        modifier = Modifier.width(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        /**
         * Remove Button*/
        Box(
            modifier = Modifier
                .padding(3.dp)
                .size(30.dp)
                .aspectRatio(1f)
                .background(
                    color = Color(225, 0, 102),
                    shape = CircleShape
                )
                .clickable {
                    onDecreaseQuantity.invoke()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Add",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        /**
         * Quantity
         * */
        Text(
            text = quantity.toString(),
            color = Color(225, 0, 102),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        /**
         * Add Button
         * */
        Box(modifier = Modifier
            .padding(3.dp)
            .size(30.dp)
            .aspectRatio(1f)
            .background(
                color = Color(225, 0, 102),
                shape = CircleShape
            )
            .clickable {
                onIncreaseQuantity.invoke()
            },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
            )
        }
    }
}

@Composable
fun AddButton(
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(255, 0, 102)),
        modifier = Modifier.height(32.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Button")
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ADD",
                    fontSize = 12.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Deals of the Week",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                tint = Color.White,
                contentDescription = "Back"
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(225, 0, 102)
        )
    )
}


@Composable
fun HeaderWithActions() {
    Row(
        modifier = Modifier
            .height(35.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = LocalContext.current.getString(R.string.twenty_products_found),
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Rounded.Sort,
            contentDescription = "Filter Icon"
        )

        Spacer(modifier = Modifier.width(0.dp))

        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Rounded.Tune,
            contentDescription = "Filter Icon"
        )
    }
}


