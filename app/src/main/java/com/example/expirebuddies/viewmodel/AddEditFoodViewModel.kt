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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddEditFoodViewModel(
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

            }

            is UiAddEditFoodEvent.EnteredExpiryDate -> {
                _foodExpiryDate.value = event.date
            }

            is UiAddEditFoodEvent.AddFoodEvent -> {
                try {

                    addFood(
                        Food(
                            null,
                            foodName.value,
                            foodExpiryDate.value,
                        )
                    )
                }catch (error:InvalidFood){

                }
            }

        }
    }

    suspend fun addFood(food: Food) {
        viewModelScope.launch {
            foodManipulationUseCases.addFood.invoke(food)
        }
    }


}


sealed class UiAddEditFoodEvent {
    data class EnteredFoodName(val name: String) : UiAddEditFoodEvent()
    data class EnteredExpiryDate(val date: String) : UiAddEditFoodEvent()
    object AddFoodEvent : UiAddEditFoodEvent()

}



