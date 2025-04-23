package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expirebuddies.view.components.AddFoodDialog
import com.example.expirebuddies.view.components.AddFoodFloatingButton
import com.example.expirebuddies.view.components.FoodList
import com.example.expirebuddies.view.components.mockFoodList


@Composable
fun MainScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var foodName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        FoodList(items = mockFoodList)
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
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}