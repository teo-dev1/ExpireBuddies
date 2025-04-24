package com.example.expirebuddies.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.expirebuddies.model.FoodItem


@Composable
    fun FoodInputField(
        text: String,
        onTextChange: (String) -> Unit
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text("Inserisci alimento") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

    @Composable
    fun ExpiringDateInputField(
        text: String,
        onTextChange: (String) -> Unit
    ){
        TextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text("Inserisci data di scadenza") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

    @Composable
    fun ConfirmButton(onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Conferma")
        }
    }

@Composable
fun AddFoodDialog(
    foodName:String,
    onFoodNameChange: (String) -> Unit,
    expiryDate: String,
    onExpiryDateChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                FoodInputField(
                    text = foodName,
                    onTextChange = onFoodNameChange
                )

                ExpiringDateInputField(
                    text = expiryDate,
                    onTextChange = onExpiryDateChange
                )

                ConfirmButton(onClick =
                onConfirm)
            }
        }
    }
}





