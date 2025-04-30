package com.example.expirebuddies.model.usecases

import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation

class DeleteFood(
    val localRepositoryImplementation: LocalRepositoryImplementation
) {
    suspend operator fun invoke(food: Food){
        localRepositoryImplementation.deleteFood(food)
    }
}