package com.example.expirebuddies.view

import com.example.expirebuddies.general.OrderType
import com.example.expirebuddies.model.database.Food

data class FoodState(val foods:List<Food> = emptyList(),
                     val foodOrder: OrderType = OrderType.Descending,
                     )