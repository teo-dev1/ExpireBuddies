package com.example.expirebuddies.view.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.expirebuddies.model.database.Food





@Composable
fun FoodList(foods: List<Food>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(foods) { food ->
            FoodItem(food = food)
        }
    }
}

@Composable
fun FoodItem(food: Food){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = food.name, style = MaterialTheme.typography.titleLarge, color = Color.White)
            Text(text = "Scadenza: ${food.expiryDate}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(text = "Aggiunto il: ${food.timestamp}",style =MaterialTheme.typography.bodySmall, color = Color.White)
        }
    }
}



