package com.example.exmencrud

data class Producto(
    val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
    val category: String
) 