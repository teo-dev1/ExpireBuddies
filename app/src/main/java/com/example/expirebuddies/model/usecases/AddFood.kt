package com.example.expirebuddies.model.usecases

import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation

class AddFood(
    val repositoryImplementation: LocalRepositoryImplementation
) {
    suspend operator fun invoke(food: Food){
        repositoryImplementation.addFood(food)
    }
}