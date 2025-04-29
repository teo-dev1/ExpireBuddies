package com.example.expirebuddies.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert
    fun addFood(food: Food)
    @Query("SELECT * FROM food_table ORDER BY expiryDate")
    fun getAllFoods():List<Food>
    @Query("SELECT * FROM food_table WHERE id=:id")
    fun getFoodById(id:Int):Food
    @Delete
    fun deleteFood(food: Food)
}