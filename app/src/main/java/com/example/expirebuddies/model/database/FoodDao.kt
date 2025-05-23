package com.example.expirebuddies.model.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Upsert
    fun addFood(food: Food?)
    @Query("SELECT * FROM food_table ORDER BY expiryDate")
    fun getAllFoods(): Flow<List<Food>>
    @Query("SELECT * FROM food_table WHERE id=:id")
    fun getFoodById(id:Int):Food
    @Delete
    fun deleteFood(food: Food)
}