package com.example.expirebuddies.model.usecases

import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import kotlinx.coroutines.flow.Flow

class GetFoods(
    val repositoryImplementation: LocalRepositoryImplementation
) {

    suspend operator fun invoke(): Flow<List<Food>> {
        return repositoryImplementation.getFoods()
    }
}