package com.example.expirebuddies.model.usecases

import androidx.compose.ui.res.stringResource
import com.example.expirebuddies.R
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation

class AddFood(
    val repositoryImplementation: LocalRepositoryImplementation
) {
    @kotlin.jvm.Throws(InvalidFood::class)
    suspend operator fun invoke(food: Food?){
        if(food!!.name.isBlank()) throw InvalidFood("")
        if(food!!.expiryDate.isBlank())throw  InvalidFood("")
        repositoryImplementation.addFood(food)
    }
}

class InvalidFood(errorMessage: String) : Exception(errorMessage)