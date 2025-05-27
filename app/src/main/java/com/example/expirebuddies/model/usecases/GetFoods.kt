package com.example.expirebuddies.model.usecases

import com.example.expirebuddies.model.OrderType
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.localrepository.LocalRepositoryImplementation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFoods(
    val repositoryImplementation: LocalRepositoryImplementation
) {

     operator fun invoke(orderType: OrderType=OrderType.Descending): Flow<List<Food>> {
        return repositoryImplementation.getFoods().map { foods ->
            when (orderType){
                OrderType.Ascending -> {foods.sortedBy {it.timestamp  } }
                OrderType.Descending -> {foods.sortedByDescending { it.timestamp }}
            }
        }
    }
}