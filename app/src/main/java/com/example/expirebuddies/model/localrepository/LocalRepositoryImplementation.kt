package com.example.expirebuddies.model.localrepository

import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.database.FoodDao
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImplementation(
    private val dao:FoodDao
):LocalRepository {
    override fun getFoods(): Flow<List<Food>> {
        return dao.getAllFoods()
    }

    override suspend fun addFood(food: Food?) {
        dao.addFood(food)
    }

    override suspend fun getFoodbyId(id: Int): Food {
        return dao.getFoodById(id)
    }

    override suspend fun deleteFood(food: Food) {
        dao.deleteFood(food)
    }
}