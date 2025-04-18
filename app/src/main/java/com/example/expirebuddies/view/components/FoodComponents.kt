package com.example.expirebuddies.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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
    fun ExpiringDate(
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
