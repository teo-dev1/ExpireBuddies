package com.example.expirebuddies.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expirebuddies.model.OrderType
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
) : ViewModel() {


    private val _foods = MutableStateFlow<List<Food>>(emptyList())
    val foods: StateFlow<List<Food>> = _foods

    private var getFoodsJob: Job? = null //tengo traccia della coroutine che chiamo per avere la lista dei cibi, cosicchè se dovesse essere già stata chiamata in precedenza la cancello e ne avvio una nuova

    private var lastDeletedFood: Food? = null

    private val _selectedFood = MutableStateFlow<Food?>(null)
    val selectedFood: StateFlow<Food?> = _selectedFood

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen

    private val _isFoodSelected = MutableStateFlow(false)
    val isFoodSelected=_isFoodSelected

    //TODO diversificare le azioni dell utente, e capire cosa fare in base ad essa utilizzando la classe useraction creata sotto


    init {
        getFoods(OrderType.Descending)
    }

     fun onEvent(event: FoodEvent) {
        when (event) {
            is FoodEvent.DeleteFood -> {
                deleteFood(event.food)
                lastDeletedFood = event.food
            }
            is FoodEvent.Order -> {
                getFoods(event.order)
            }
            FoodEvent.RestoreFood -> {
                viewModelScope.launch {
                    addFood(lastDeletedFood)
                    lastDeletedFood = null
                }
            }
            is FoodEvent.FoodSelected -> {
                openDialog()
                foodSelected()
                viewModelScope.launch(Dispatchers.IO) {
                    val food: Food = useCases.getFood.invoke(event.id)!!
                    _selectedFood.value = food
                }
            }
            is FoodEvent.FoodNotSelected -> {
               foodNotSelected()
            }
        }
    }


    fun openDialog(){
        _isDialogOpen.value = true
    }

    fun foodSelected(){
        _isFoodSelected.value=true
    }

    fun foodNotSelected(){
        _isFoodSelected.value=false
    }


    fun dialogClosed(){
        _isDialogOpen.value = false
    }

    fun onDialogDismiss(){
        _isDialogOpen.value = false
        _selectedFood.value = null
    }




    fun getFoods(order: OrderType) {
        getFoodsJob?.cancel()
        getFoodsJob = viewModelScope.launch {
            useCases.getFoods(order)
                .onEach { foods ->
                    _foods.value = foods
                }.launchIn(viewModelScope)
        }
    }

    fun addFood(food: Food?) { //va in altro viewmodel
        viewModelScope.launch {
            useCases.addFood(food)
        }
    }

    fun deleteFood(food: Food) {
        viewModelScope.launch {
            useCases.deleteFood(food = food)
        }
    }

}


sealed class FoodEvent() {
    data class Order(val order: OrderType) : FoodEvent()
    data class DeleteFood(val food: Food) : FoodEvent()
    data class FoodSelected(val id: Int) : FoodEvent()
    object FoodNotSelected:FoodEvent()
    object RestoreFood : FoodEvent()


}

sealed class FoodState
