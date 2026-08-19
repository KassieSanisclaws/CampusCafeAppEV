package com.raywenderlich.campuscafeappev.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.campuscafeappev.dataclass.MenuItem
import com.raywenderlich.campuscafeappev.model.Category

@Composable
fun MenuScreen() {
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
            category = Category.DRINK
        ),
        MenuItem(
            id = 3,
            name = "Sour Creme Glazed Donut",
            price = 1.50,
            category = Category.DESSERT
        ),
        MenuItem(
            id = 4,
            name = "Italian Meatball Sandwich",
            price = 6.50,
            category = Category.FOOD
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text ( text = "Campus Cafe Menu" )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(menuItems) { item ->
               MenuItemCard( item = item)
            }
        }
    }
}

@Composable
fun MenuItemCard( item: MenuItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text( text = item.name )
            Text( text = "Category: ${ item.category }")
            Text( text = "$${ item.price }")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button( onClick = {
                                    // TO Be Implemented
                                 }) {
                    Text( text = "View Details ")
                }
            }
        }
    }
}