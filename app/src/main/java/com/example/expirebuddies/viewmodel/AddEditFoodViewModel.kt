package com.example.expirebuddies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditFoodViewModel @Inject constructor(
    private val foodManipulationUseCases: FoodManipulationUseCases
) : ViewModel() {


//    private val _foodName = mutableStateOf("")
//    val foodName: State<String> = _foodName
//
//    private val _foodExpiryDate = mutableStateOf("")
//    val foodExpiryDate: State<String> = _foodExpiryDate
//
//
//    private val _eventFlow = MutableSharedFlow<UiAddEditFoodEvent>()
//    val eventFlow = _eventFlow.asSharedFlow()


 //   suspend fun onEvent(event: UiAddEditFoodEvent) {
//        when (event) {
//            is UiAddEditFoodEvent.EnteredFoodName -> {
//                _foodName.value = event.name
//                println("sto cambiando nome valore attuale -> ${_foodName.value}" )
//
//            }
//
//            is UiAddEditFoodEvent.EnteredExpiryDate -> {
//                _foodExpiryDate.value = event.date
//                println("sto cambiando data valore attuale -> ${_foodExpiryDate.value}" )
//            }
//
//            is UiAddEditFoodEvent.AddFoodEvent -> {
//                println("ho aggiunto "+ foodName.value + " " + foodExpiryDate.value)
//                try {
//                    viewModelScope.launch(Dispatchers.IO){
//                        addFood(
//                            Food(
//                                null,
//                                foodName.value,
//                                foodExpiryDate.value,
//                                System.currentTimeMillis()
//                            )
//                        )
//                    }
//
//                }catch (error:InvalidFood){
//                    println("PROBLEMI PROBLEMI PROBLEMI!")
//                }
//            }
//            is UiAddEditFoodEvent.DeleteFood -> {}
//            is UiAddEditFoodEvent.FoodSelected -> {}
//            is UiAddEditFoodEvent.Order -> {}
//        }
//    }

    suspend fun addFood(food: Food) {
            foodManipulationUseCases.addFood.invoke(food)
    }

    suspend fun getFoodById(id: Int){
        foodManipulationUseCases.getFood.invoke(id)
    }


}


//sealed class UiAddEditFoodEvent {
//    data class EnteredFoodName(val name: String) : UiAddEditFoodEvent()
//    data class EnteredExpiryDate(val date: String) : UiAddEditFoodEvent()
//    object AddFoodEvent : UiAddEditFoodEvent()
//
//    //importate
//    data class Order(val order: OrderType) : UiAddEditFoodEvent()
//    data class DeleteFood(val food: Food) : UiAddEditFoodEvent()
//    data class FoodSelected(val id: Int) : UiAddEditFoodEvent()
//    object FoodNotSelected:FoodEvent()
//    object RestoreFood : FoodEvent()
//
//}



