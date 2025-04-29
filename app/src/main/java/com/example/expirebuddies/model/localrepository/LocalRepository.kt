package com.example.expirebuddies.model.localrepository

import com.example.expirebuddies.model.database.Food

interface LocalRepository {
     suspend fun getFoods():List<Food>
     suspend fun addFood(food: Food)
     suspend fun getFoodbyId(id:Int):Food
     suspend fun deleteFood(food: Food)
}