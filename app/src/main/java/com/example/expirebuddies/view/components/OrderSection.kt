package com.example.expirebuddies.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.expirebuddies.R
import com.example.expirebuddies.general.OrderType

@Composable
fun OrderSection(
    modifier: Modifier=Modifier,
    order: OrderType = OrderType.Descending,
){
    Column(
        modifier = modifier
    ){
       Row(
           modifier = Modifier.fillMaxWidth()
       ) {
           OrderRadioButton(
               text = stringResource(id = R.string.most_recent),
               selected = order is OrderType.Descending,
               onSelect = {
               })
           Spacer(modifier = Modifier.width(8.dp))
           OrderRadioButton(
               text = stringResource(id = R.string.least_recent),
               selected = order is OrderType.Ascending,
               onSelect = {
               })
       }

    }

}