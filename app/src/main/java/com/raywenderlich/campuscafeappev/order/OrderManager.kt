package com.raywenderlich.campuscafeappev.order

import androidx.compose.runtime.mutableStateListOf
import com.raywenderlich.campuscafeappev.dataclass.MenuItem
import com.raywenderlich.campuscafeappev.dataclass.OrderItem

class OrderManager {
    // Order State:
    // MutableStateListOf and it is a special compose collection.
    // When items are added, removed, or changed , compose knows that the ui may need to be rerendered.
    // A normal mutableListOf and it does NOt compose.
    private val orderItems = mutableStateListOf<OrderItem>()


    // Read-only access to the current order.
    // Other classes can read the order but cannot directly modify the mutable list.
    val items: List<OrderItem> get() = orderItems


    fun addItem(menuItem: MenuItem) {
        // Check whether this menu item is already in the order.
        val existingItem = orderItems.find { orderItem ->
            orderItem.menuItem.id == menuItem.id
        }
        if (existingItem != null) {
            // Find the position of the existing item.
            val indx = orderItems.indexOf(existingItem)

            // Replace the existing OrderItem with a new one containing an increased quantity.
            orderItems[indx] = existingItem.copy(
                quantity = existingItem.quantity + 1
            )
        } else {
            // The item is not currently in the order, so we are going to create a new orderItem.
            orderItems.add(
                OrderItem(
                    menuItem = menuItem,
                    quantity = 1
                )
            )
        }
    }

    // Function removing Menu Item.
        fun removeItem(menuItem: MenuItem) {

        // Find Item in the order
        val existingItem = orderItems.find { orderItem ->
            orderItem.menuItem.id == menuItem.id
        }
        if (existingItem != null) {

            if (existingItem.quantity > 1) {
                // There is more than one item.
                // Reduce the quantity by one.
                val indx = orderItems.indexOf(existingItem)

                orderItems[indx] = existingItem.copy(
                    quantity = existingItem.quantity - 1
                )

            } else {
                // Only one remains, so remove the
                // entire OrderItem from the list.
                orderItems.remove(existingItem)
            }
        }
    }

    fun clearOrder() {
        // Remove everything from the order.
        orderItems.clear()
    }

    fun getTotal(): Double {
        return orderItems.sumOf { orderItem ->
            orderItem.menuItem.price * orderItem.quantity
        }
    }
}