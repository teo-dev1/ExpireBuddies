package com.example.expirebuddies.general

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
