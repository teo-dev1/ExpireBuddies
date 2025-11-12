package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expirebuddies.model.database.Food
import com.example.expirebuddies.view.components.FoodDialog
import com.example.expirebuddies.view.components.AddFoodFloatingButton
import com.example.expirebuddies.view.components.FoodList
import com.example.expirebuddies.viewmodel.AddEditFoodViewModel
import com.example.expirebuddies.viewmodel.FoodEvent
import com.example.expirebuddies.viewmodel.FoodMainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Preview
@Composable
fun Home(
    /*navController: NavController,*/
    mainViewModel: FoodMainViewModel = hiltViewModel(),
    addEditFoodViewModel: AddEditFoodViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val isDialogOpen by mainViewModel.isDialogOpen.collectAsState()
    val foodList by mainViewModel.foods.collectAsState()
    val selectedFood by mainViewModel.selectedFood.collectAsState()
    val isFoodSelected by mainViewModel.isFoodSelected.collectAsState()//tiene in memoria se deve mostrare la dialog con i dati per modificare il cibo che ho selezionato dalla lista o senza, per aggiungerne uno nuovo perchè è stato premuto il bottone add
    var name = mainViewModel.foodName.value
    var expiryDate = mainViewModel.foodExpiryDate.value

    //prova

    //prova


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Bottone in alto a destra (in una Box per allinearlo)
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { /* azione */ },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {

                Text("Clicca")

            }
        }

        // Lista (occupando il resto dello spazio)
        Box(modifier = Modifier.weight(1f)) {
            FoodList(
                foods = foodList,
                onItemClick = { food ->
                    mainViewModel.onEvent(FoodEvent.FoodSelected(food.id!!))
                }
            )

            Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                AddFoodFloatingButton(onClick = { mainViewModel.openDialog() })
            }
        }
    }

        if (isDialogOpen) {
            FoodDialog(
                foodName = name,
                onFoodNameChange = {
                    scope.launch {
                        mainViewModel.onEvent(FoodEvent.EnteredFoodName(it))
                    }
                },
                expiryDate = expiryDate,
                onExpiryDateChange = {
                    scope.launch {
                        mainViewModel.onEvent(FoodEvent.EnteredExpiryDate(it))
                    }
                },
                onConfirm = {
                    // Qui salvi i dati, es. nel DB
                    //isDialogOpen
                    scope.launch(Dispatchers.IO) {
                        mainViewModel.onEvent(FoodEvent.AddFoodEvent)
                    }
                    mainViewModel.dialogClosed()
                    //mockFoodList.add(Food(foodName,expiryDate)) //dovrà aggiungere cibi al database tramite viewmodel
                },
                onDismiss = {
                    mainViewModel.dialogClosed()
                    //mainViewModel.selectedFood
                }
            )
        }
    }


public var mockFoodList = mutableListOf<Food>(
    Food(null, "Latte", "20/04/2025", System.currentTimeMillis()),
    Food(null, "Pane", "19/04/2025", System.currentTimeMillis()),
    Food(null, "Formaggio", "22/04/2025", System.currentTimeMillis()),
    Food(null, "Uova", "21/04/2025", System.currentTimeMillis()),
    Food(null, "Yogurt", "23/04/2025", System.currentTimeMillis())
)
