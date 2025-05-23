package com.example.expirebuddies.model.usecases

import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation

class GetFood(
    val repositoryImplementation: LocalRepositoryImplementation
) {
    suspend operator fun invoke(id:Int): Food?{
        return repositoryImplementation.getFoodbyId(id)
    }
}