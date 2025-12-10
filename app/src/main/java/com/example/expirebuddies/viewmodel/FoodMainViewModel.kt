package com.example.expirebuddies.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expirebuddies.general.OrderType
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.remotedata.remoterepository.RemoteRepository
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import com.example.expirebuddies.model.usecases.InvalidFood
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodMainViewModel @Inject constructor(
    private val useCases: FoodManipulationUseCases,
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

    private val _foodName = mutableStateOf("")
    val foodName: State<String> = _foodName

    private val _foodExpiryDate = mutableStateOf("")
    val foodExpiryDate: State<String> = _foodExpiryDate


    private val _eventFlow = MutableSharedFlow<FoodEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                    _selectedFood.value = food.copy()
                    _foodName.value=food.name
                    _foodExpiryDate.value=food.expiryDate
                }
            }
            is FoodEvent.FoodNotSelected -> {
               foodNotSelected()
            }
            is FoodEvent.EnteredFoodName -> {
                _foodName.value = event.name
                println("sto cambiando nome valore attuale -> ${_foodName.value}" )

            }

            is FoodEvent.EnteredExpiryDate -> {
                _foodExpiryDate.value = event.date
                println("sto cambiando data valore attuale -> ${_foodExpiryDate.value}" )
            }

            is FoodEvent.AddFoodEvent -> {
                println("ho aggiunto "+ foodName.value + " " + foodExpiryDate.value)
                try {
                    viewModelScope.launch(Dispatchers.IO){

                        addFood(
                            Food(
                                if (_isFoodSelected.value)selectedFood.value.let {it?.id} else null,
                                foodName.value,
                                foodExpiryDate.value,
                                System.currentTimeMillis()
                            )
                        )
                    }

                }catch (error: InvalidFood){
                    println("PROBLEMI PROBLEMI PROBLEMI!")
                }
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
        _selectedFood.value=null
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

    suspend fun addFood(food: Food) {
        useCases.addFood.invoke(food)
    }

    suspend fun getFoodById(id: Int){
        useCases.getFood.invoke(id)
    }

}


sealed class FoodEvent() {
    data class Order(val order: OrderType) : FoodEvent()
    data class DeleteFood(val food: Food) : FoodEvent()
    data class FoodSelected(val id: Int) : FoodEvent()
    object FoodNotSelected:FoodEvent()
    object RestoreFood : FoodEvent()

    //importate
    data class EnteredFoodName(val name: String) : FoodEvent()
    data class EnteredExpiryDate(val date: String) : FoodEvent()
    object AddFoodEvent : FoodEvent()
}

sealed class FoodState
