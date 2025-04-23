package com.example.expirebuddies.view.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


    @Composable
    fun AddFoodFloatingButton(onClick: () -> Unit) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp)
            , // Spaziatura per distanziare dal bordo
            containerColor = MaterialTheme.colorScheme.primary // Colore di sfondo del pulsante
        ) {
            Icon(
                imageVector = Icons.Default.Add, // Icona che rappresenta l'aggiunta
                contentDescription = "Add Food"
            )
        }
    }
