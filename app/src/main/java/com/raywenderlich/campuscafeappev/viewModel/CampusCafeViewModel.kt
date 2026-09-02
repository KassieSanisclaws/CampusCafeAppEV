package com.raywenderlich.campuscafeappev.viewModel

import androidx.lifecycle.ViewModel
import com.raywenderlich.campuscafeappev.dataclass.MenuItem
import com.raywenderlich.campuscafeappev.dataclass.OrderItem
import com.raywenderlich.campuscafeappev.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CampusCafeViewModel: ViewModel() {
    // MENU - Idea is the cafe menu is going to contain the application data.
    // MENU-UI:
    val menuItems = listOf(
        MenuItem(
            id = 1,
            name = "Coffee",
            price = 2.50,
            category = Category.DRINK
        ),
        MenuItem(
            id = 2,
            name = "Tea",
            price = 2.00,
            category = Category.DRINK ),
        MenuItem(
            id = 3,
            name = "Donut",
            price = 1.75,
            category = Category.DESSERT ),
        MenuItem(
            id = 4,
            name = "Sandwich",
            price = 6.50,
            category = Category.FOOD
        )
    )

    // ORDER STATE:
    private val _orderItems = MutableStateFlow<List<OrderItem>>(emptyList())

    // STATE-Flow - Read-Only
    val orderItems: StateFlow<List<OrderItem>> = _orderItems.asStateFlow()

    // TOTAL STATE:
    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total.asStateFlow()

    // STUDENT POINTS:
    // Store The Current Points:
    private val _points = MutableStateFlow(0)

    // READ-Only - Version For UI:
    val points: StateFlow<Int> = _points.asStateFlow()

    // ADD ITEM:
    fun addItem(
        menuItem: MenuItem
    ) {
        // Holds Current Order:
        val currntOrdr = _orderItems.value
        // Existing Item:
        val existingItem = currntOrdr.find {
            it.menuItem.id == menuItem.id
        }

        if (existingItem != null) {
           val updatedOrdr = currntOrdr.map { orderItem ->
               if (orderItem.menuItem.id == menuItem.id) {
                   orderItem.copy(quantity = orderItem.quantity + 1)
               } else {
                   orderItem
               }
           }
            _orderItems.value = updatedOrdr
        } else {
            val updatedOrder = currntOrdr + OrderItem(
                menuItem = menuItem,
                quantity = 1
            )
            _orderItems.value = updatedOrder
        }
        // Re-Calculate & Get Total:
        getTotal()
    }

    // REMOVE One Item:
    fun removeItem(
        menuItem: MenuItem
    ) {
        val currntOrder = _orderItems.value
        val existingItem = currntOrder.find {
            it.menuItem.id == menuItem.id
        }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                val updatedOrder = currntOrder.map { orderItem ->
                    if (orderItem.menuItem.id == menuItem.id) {
                        orderItem.copy(quantity = orderItem.quantity -1)
                    } else {
                        orderItem
                    }
                }
                _orderItems.value = updatedOrder
            } else {
                val updatdOrdr = currntOrder.filter {
                    it.menuItem.id != menuItem.id
                }
                _orderItems.value = updatdOrdr
            }
            // RE-CALCULATE:
            getTotal()
        }
    }
    // INCREASE QUANTITY:
    fun increaseQuantity(
        menuItem: MenuItem
    ) {
        val currentOrder = _orderItems.value
        val updatedOrder = currentOrder.map { orderItem ->
            if (orderItem.menuItem.id == menuItem.id) {
                orderItem.copy(
                    quantity = orderItem.quantity + 1
                )
            } else {
                orderItem
            }
        }
        _orderItems.value = updatedOrder

        // RE-CALCULATE TOTAL:
        getTotal()
    }

    // DECREASE QUANTITY:
    fun decreaseQuantity(
        menuItem: MenuItem
    ) {
        val currentOrder = _orderItems.value
        val existingItem = currentOrder.find {
            it.menuItem.id == menuItem.id
        }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
             // REDUCE the quantity by 1:
             val updatedOrder = currentOrder.map { orderItem ->
                 if (orderItem.menuItem.id == menuItem.id) {
                     orderItem.copy(
                         quantity = orderItem.quantity - 1
                     )
                 } else {
                     orderItem
                 }
              }
              _orderItems.value = updatedOrder
            } else {
                // If Quantity is already 1 remove the item from the order.
                val updatedOrder = currentOrder.filter {
                    it.menuItem.id != menuItem.id
                }
                _orderItems.value= updatedOrder
            }
            //RE-CALCULATE:
            getTotal()
        }
    }


    // CLEAR Order:
    fun clearOrder() {
        _orderItems.value = emptyList()
        // RESET The Total:
        _total.value = 0.0
    }
    // TOTAL:
    private fun getTotal() {
        _total.value = _orderItems.value.sumOf { orderItem ->
            orderItem.menuItem.price * orderItem.quantity
        }
    }
    // AWARD-Points:
    fun addPoints(amount: Int) {
        _points.value += amount
    }
}