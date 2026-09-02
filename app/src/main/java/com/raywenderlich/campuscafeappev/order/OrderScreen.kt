package com.raywenderlich.campuscafeappev.order

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.campuscafeappev.dataclass.OrderItem

@Composable
fun OrderScreen(
    orderItems: List<OrderItem>,
    total: Double,
    onIncreaseQuantity: (OrderItem) -> Unit,
    onDecreaseQuantity: (OrderItem) -> Unit,
    onRemoveItem: (OrderItem) -> Unit,
    onClearOrder: () -> Unit,
    onCheckout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Your Order")
           if(orderItems.isEmpty()){
                Text(text = "No Order's - Your Cart Is Empty!",
                    modifier = Modifier.padding(top = 16.dp))
              } else {
                  LazyColumn(
                      modifier = Modifier
                          .weight(1f)
                          .padding(top = 16.dp),
                      verticalArrangement = Arrangement.spacedBy(12.dp)
                  ) {
                      items(orderItems) { orderItem ->
                          OrderItemCard(
                              orderItem = orderItem,
                              onIncreaseQuantity = onIncreaseQuantity,
                              onDecreaseQuantity = onDecreaseQuantity,
                              onRemoveItem = onRemoveItem
                          )
                      }
                  }
               Text(text = "Total: $${"%.2f" .format(total)}", modifier = Modifier.padding(top = 16.dp))
               Button(
                   onClick = onClearOrder,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(top = 8.dp)
               ) {
                   Text(text = "Clear Order")
               }
               Button(
                   onClick = onCheckout,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(top = 8.dp)
               ) {
                   Text(text = "CheckOut")
               }
           }
      }
}

@Composable
fun OrderItemCard(
    orderItem: OrderItem,
    onIncreaseQuantity: (OrderItem) -> Unit,
    onDecreaseQuantity: (OrderItem) -> Unit,
    onRemoveItem: (OrderItem) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Menu Item Name:
            Text(text = orderItem.menuItem.name)
            // Quantity:
            Text(
                text = "Quantity: ${orderItem.quantity}",
                modifier = Modifier.padding(top = 8.dp)
                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                // DECREASE Button:
                Button(
                    onClick = {
                        onDecreaseQuantity(orderItem)
                    }
                ) {
                    Text(text = "-")
                }
                // CURRENT Quantity
                Text(
                    text = "${orderItem.quantity}",
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    )
                )
                // INCREASE Button
                Button(
                    onClick = {
                        onIncreaseQuantity(orderItem)
                    }
                ) {
                    Text(text = "+")
                }
            }
            // Price for the item:
            Text(text = "Price: $${"%.2f".format(orderItem.menuItem.price)}")
            // Price for all items
            Text(text = "Subtotal: $${"%.2f" .format(
                orderItem.menuItem.price * orderItem.quantity
            )}")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            onRemoveItem(orderItem)
                        }
                    ) {
                        Text(text = "Remove Item")
                    }
                }
          }
     }
}