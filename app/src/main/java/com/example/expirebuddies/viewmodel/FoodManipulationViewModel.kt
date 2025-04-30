package com.example.expirebuddies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.usecases.AddFood
import com.example.expirebuddies.model.usecases.DeleteFood
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodManipulationViewModel @Inject constructor(
    private val useCases: FoodManipulationUseCases
):ViewModel() {


    private val _foods = MutableStateFlow<List<Food>>(emptyList())
    private val foods=_foods


//    fun onAction(action:UserAction){
//        when(action){
//            is UserAction.ActionAddFood -> addFood()
//        }
//    }

     //diversificare le azioni dell utente, e capire cosa fare in base ad essa utilizzando la classe useraction creata sotto





    fun getFoods(){
        viewModelScope.launch {
            useCases.getFoods()
        }
    }

    fun addFood(food: Food){
        viewModelScope.launch {
            useCases.addFood(food)
        }
    }
    fun deleteFood(food: Food){
        viewModelScope.launch {
            useCases.deleteFood(food = food)
        }
    }

}


sealed class UserAction(){
    class ActionAddFood(food: AddFood):UserAction()
    class ActionDeleteFood(food: Food):UserAction()
}