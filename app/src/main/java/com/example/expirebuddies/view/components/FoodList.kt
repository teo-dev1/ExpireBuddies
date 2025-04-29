package com.example.expirebuddies.view.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.expirebuddies.model.database.Food


public var mockFoodList = mutableListOf<Food>(
//    FoodItem("Latte", "20/04/2025"),
//    FoodItem("Pane", "19/04/2025"),
//    FoodItem("Formaggio", "22/04/2025"),
//    FoodItem("Uova", "21/04/2025"),
//   Food("Yogurt", "23/04/2025")
)


@Composable
fun FoodList(items: List<Food>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items) { item ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(text = item.name, color = Color.White)
                Text(text = "Scade il: ${item.expiryDate}",color = Color.White)
            }
        }
    }
}



