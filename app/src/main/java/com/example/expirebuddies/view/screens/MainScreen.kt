package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expirebuddies.model.OrderType
import com.example.expirebuddies.view.components.AddFoodDialog
import com.example.expirebuddies.view.components.AddFoodFloatingButton
import com.example.expirebuddies.view.components.FoodList
import com.example.expirebuddies.viewmodel.FoodMainViewModel


@Composable
fun MainScreen(
    navController: NavController,
    viewmodel:FoodMainViewModel= hiltViewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var foodName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        FoodList(items = viewmodel.getFoods(OrderType.Descending))//dovrà ricevere la lista di cibi in scadenza dal viewmodel
        Box(modifier = Modifier.align(Alignment.BottomEnd)){
            AddFoodFloatingButton(onClick = { showDialog = true })
        }

        if (showDialog) {
            AddFoodDialog(
                foodName = foodName,
                onFoodNameChange = { foodName = it },
                expiryDate = expiryDate,
                onExpiryDateChange = { expiryDate = it },
                onConfirm = {
                    // Qui salvi i dati, es. nel DB
                    showDialog = false
                    //mockFoodList.add(Food(foodName,expiryDate)) //dovrà aggiungere cibi al database tramite viewmodel
                    foodName=""
                    expiryDate=""
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
    }
}