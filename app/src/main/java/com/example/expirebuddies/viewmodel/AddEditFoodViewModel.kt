package com.example.expirebuddies.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.model.usecases.FoodManipulationUseCases
import com.example.expirebuddies.model.usecases.InvalidFood
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditFoodViewModel @Inject constructor(
    private val foodManipulationUseCases: FoodManipulationUseCases
) : ViewModel() {


    private val _foodName = mutableStateOf("")
    val foodName: State<String> = _foodName

    private val _foodExpiryDate = mutableStateOf("")
    val foodExpiryDate: State<String> = _foodExpiryDate

    private val _eventFlow = MutableSharedFlow<UiAddEditFoodEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    suspend fun onEvent(event: UiAddEditFoodEvent) {
        when (event) {
            is UiAddEditFoodEvent.EnteredFoodName -> {
                _foodName.value = event.name
                println("sto cambiando nome valore attuale -> ${_foodName.value}" )

            }

            is UiAddEditFoodEvent.EnteredExpiryDate -> {
                _foodExpiryDate.value = event.date
                println("sto cambiando data valore attuale -> ${_foodExpiryDate.value}" )
            }

            is UiAddEditFoodEvent.AddFoodEvent -> {
                println("ho aggiunto "+ foodName.value + " " + foodExpiryDate.value)
                try {
                    viewModelScope.launch(Dispatchers.IO){
                        addFood(
                            Food(
                                null,
                                foodName.value,
                                foodExpiryDate.value,
                                System.currentTimeMillis()
                            )
                        )
                    }

                }catch (error:InvalidFood){
                    println("PROBLEMI PROBLEMI PROBLEMI!")
                }
            }

        }
    }

    suspend fun addFood(food: Food) {
            foodManipulationUseCases.addFood.invoke(food)
    }


}


sealed class UiAddEditFoodEvent {
    data class EnteredFoodName(val name: String) : UiAddEditFoodEvent()
    data class EnteredExpiryDate(val date: String) : UiAddEditFoodEvent()
    object AddFoodEvent : UiAddEditFoodEvent()

}



