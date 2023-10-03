package com.example.dz2apiphotos.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dz2apiphotos.HomeViewModel

@Composable
fun AddList(
    viewModel: HomeViewModel,
    navController: NavController
){
    var name by remember { mutableStateOf(("")) }
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text("Добавить товар")
        TextField(
            value = name,
            onValueChange = { newText ->
                name = newText
            }
        )
        if (name!=""){
            Button(onClick = {viewModel.createShoppingList(viewModel.authKey, name)
                navController.navigate("home")
            }) {
                Text("Add Item")
            }
        }
    }


}