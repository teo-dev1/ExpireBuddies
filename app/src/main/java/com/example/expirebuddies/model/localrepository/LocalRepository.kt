package com.example.expirebuddies.model.localrepository

import com.example.expirebuddies.model.database.Food
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
     fun getFoods(): Flow<List<Food>>
     suspend fun addFood(food: Food?)
     suspend fun getFoodbyId(id:Int):Food
     suspend fun deleteFood(food: Food)
}