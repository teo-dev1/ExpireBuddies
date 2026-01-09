package com.example.expirebuddies.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // eventuale header, altrimenti lasci vuoto
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            // spazio centrale vuoto, ma stessa struttura della home vera
        }
    }
}
