package com.raywenderlich.campuscafeappev.dataclass

import com.raywenderlich.campuscafeappev.model.Category

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Double,
    val category: Category
)
