package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.view.components.FoodDialog
import com.example.expirebuddies.view.components.AddFoodFloatingButton
import com.example.expirebuddies.view.components.FoodList
import com.example.expirebuddies.viewmodel.AddEditFoodViewModel
import com.example.expirebuddies.viewmodel.FoodEvent
import com.example.expirebuddies.viewmodel.FoodMainViewModel
import com.example.expirebuddies.viewmodel.UiAddEditFoodEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Preview
@Composable
fun MainScreen(
    /*navController: NavController,*/
    mainViewModel: FoodMainViewModel = hiltViewModel(),
    addEditFoodViewModel: AddEditFoodViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val isDialogOpen by mainViewModel.isDialogOpen.collectAsState()
    val foodList by mainViewModel.foods.collectAsState()
    val selectedFood by mainViewModel.selectedFood.collectAsState()
    val isFoodSelected by mainViewModel.isFoodSelected.collectAsState()//tiene in memoria se deve mostrare la dialog con i dati per modificare il cibo che ho selezionato dalla lista o senza, per aggiungerne uno nuovo perchè è stato premuto il bottone add



    Box(modifier = Modifier.fillMaxSize()) {
        FoodList(foods = /*mockFoodList*/ foodList, onItemClick = {food->
            mainViewModel.onEvent(FoodEvent.FoodSelected(food.id!!))
        })//dovrà ricevere la lista di cibi in scadenza dal viewmodel
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            AddFoodFloatingButton(onClick = {isDialogOpen })
        }

        if (isDialogOpen) {
            if (!isFoodSelected) {}
                var name = addEditFoodViewModel.foodName.value
                var expiryDate = addEditFoodViewModel.foodExpiryDate.value
                FoodDialog(
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
                        scope.launch(Dispatchers.IO) {
                            addEditFoodViewModel.onEvent(UiAddEditFoodEvent.AddFoodEvent)
                        }
                        //mockFoodList.add(Food(foodName,expiryDate)) //dovrà aggiungere cibi al database tramite viewmodel
                    },
                    onDismiss = {
                        mainViewModel.selectedFood
                    }
                    isDialogOpen
                )
        }
    }
}

public var mockFoodList = mutableListOf<Food>(
    Food(null,"Latte", "20/04/2025",System.currentTimeMillis()),
    Food(null,"Pane", "19/04/2025",System.currentTimeMillis()),
    Food(null,"Formaggio", "22/04/2025",System.currentTimeMillis()),
    Food(null,"Uova", "21/04/2025",System.currentTimeMillis()),
   Food(null,"Yogurt", "23/04/2025",System.currentTimeMillis())
)
