package com.example.dz2apiphotos.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dz2apiphotos.HomeViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddList(
    viewModel: HomeViewModel,
    navController: NavController
){
    var name by remember { mutableStateOf(("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(modifier = Modifier.padding(4.dp), fontStyle = MaterialTheme.typography.h4.fontStyle ,text = "Создать список")
        OutlinedTextField(
            value = name,
            label = { Text(text = "Название") },
            onValueChange = { name = it },
            textStyle = MaterialTheme.typography.caption,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            colors = testTextFieldColors(),
            singleLine = true,
        )
        if (name!=""){
            Button(onClick = {viewModel.createShoppingList(viewModel.authKey, name)
                navController.popBackStack()
                navController.navigate("home")

            }) {
                Text("Добавить список")
            }
        }
    }


}