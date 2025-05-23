package com.example.expirebuddies.model

import androidx.room.Index

sealed class OrderType{
    object Ascending:OrderType()
    object Descending:OrderType()
}
