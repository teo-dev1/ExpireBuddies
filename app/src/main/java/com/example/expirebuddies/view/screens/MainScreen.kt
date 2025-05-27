package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expirebuddies.model.OrderType
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.view.components.AddFoodDialog
import com.example.expirebuddies.view.components.AddFoodFloatingButton
import com.example.expirebuddies.view.components.FoodList
import com.example.expirebuddies.viewmodel.AddEditFoodViewModel
import com.example.expirebuddies.viewmodel.FoodMainViewModel
import com.example.expirebuddies.viewmodel.UiAddEditFoodEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



public var mockFoodList = mutableListOf<Food>(
    Food(null,"Latte", "20/04/2025",System.currentTimeMillis()),
    Food(null,"Pane", "19/04/2025",System.currentTimeMillis()),
    Food(null,"Formaggio", "22/04/2025",System.currentTimeMillis()),
    Food(null,"Uova", "21/04/2025",System.currentTimeMillis()),
   Food(null,"Yogurt", "23/04/2025",System.currentTimeMillis())
)

@Composable
fun MainScreen(
    /*navController: NavController,*/
    mainViewModel: FoodMainViewModel = hiltViewModel(),
    addEditFoodViewModel: AddEditFoodViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        val foodList by mainViewModel.foods.collectAsState()
        FoodList(foods = /*mockFoodList*/ foodList)//dovrà ricevere la lista di cibi in scadenza dal viewmodel
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            AddFoodFloatingButton(onClick = { showDialog = true })
        }

        if (showDialog) {
            var name = addEditFoodViewModel.foodName.value
            var expiryDate = addEditFoodViewModel.foodExpiryDate.value


            AddFoodDialog(
                foodName = name,
                onFoodNameChange = {
                    scope.launch {
                        addEditFoodViewModel.onEvent(UiAddEditFoodEvent.EnteredFoodName(it))
                    }
                },
                expiryDate = expiryDate,
                onExpiryDateChange = {
                    scope.launch {
                        addEditFoodViewModel.onEvent(UiAddEditFoodEvent.EnteredExpiryDate(it))
                    }
                },
                onConfirm = {
                    // Qui salvi i dati, es. nel DB
                    showDialog = false
                    scope.launch() {
                        addEditFoodViewModel.onEvent(UiAddEditFoodEvent.AddFoodEvent)
                    }
                    //mockFoodList.add(Food(foodName,expiryDate)) //dovrà aggiungere cibi al database tramite viewmodel
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
    }
}