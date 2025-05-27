package com.example.expirebuddies.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expirebuddies.model.OrderType
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodMainViewModel @Inject constructor(
    private val useCases: FoodManipulationUseCases
):ViewModel() {


    private val _foods = MutableStateFlow<List<Food>>(emptyList())
    val foods:StateFlow<List<Food>> = _foods

    private var getFoodsJob: Job?=null //tengo traccia della coroutine che chiamo per avere la lista dei cibi, cosicchè se dovesse essere già stata chiamata in precedenza la cancello e ne avvio una nuova

    private var lastDeletedFood:Food?=null

     //TODO diversificare le azioni dell utente, e capire cosa fare in base ad essa utilizzando la classe useraction creata sotto


    init {
        getFoods(OrderType.Descending)
    }

   suspend fun onEvent(event: FoodEvent){
        when (event){
            is FoodEvent.DeleteFood -> {
                deleteFood(event.food)
                lastDeletedFood=event.food
            }
            is FoodEvent.Order ->{
                getFoods(event.order)
            }
            FoodEvent.RestoreFood -> {
                viewModelScope.launch {
                    addFood(lastDeletedFood)
                    lastDeletedFood=null
                }
            }
        }
    }


    fun getFoods(order: OrderType){
        getFoodsJob?.cancel()
        getFoodsJob=viewModelScope.launch {
            useCases.getFoods(order)
                .onEach { foods ->
                    _foods.value= foods
                }.launchIn(viewModelScope)
        }
    }

    fun addFood(food: Food?){ //va in altro viewmodel
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


sealed class FoodEvent(){
    data class Order(val order:OrderType):FoodEvent()
    data class DeleteFood(val food: Food):FoodEvent()
    object RestoreFood:FoodEvent()



}

sealed class FoodState
