package com.example.dz2apiphotos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dz2apiphotos.model.ListItem
import kotlinx.coroutines.delay
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import com.example.dz2apiphotos.model.ShoppingList
import com.example.dz2apiphotos.ui.theme.ErrorScreen
import com.example.dz2apiphotos.ui.theme.LoadingScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SelectedList(
    list_id: Int,
    homeViewModel: HomeViewModel,
    navController: NavController
){
    val state by homeViewModel.currentListState.collectAsState()
    when (state) {
        is ShoppingUiState.Loading -> LoadingScreen(modifier = Modifier)
        is ShoppingUiState.Success -> {PhotosGridScreen((state as ShoppingUiState.Success).shoppingList, modifier = Modifier, navController, homeViewModel, list_id)}
        is ShoppingUiState.Error -> ErrorScreen(modifier = Modifier, homeViewModel.getShoppingList(list_id))
    }
}
@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotosGridScreen(shoppingList: MutableList<ListItem>, modifier: Modifier = Modifier, navController: NavController, viewModel: HomeViewModel, list_id: Int) {
    val configuration = LocalConfiguration.current
    var cellsNumber = 1
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
        cellsNumber = 2
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellsNumber),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        item{
            LaunchedEffect(Unit) {
                while(true) {
                    viewModel.updateShoppingList(list_id)
                    delay(15000)
                }
            }
        }
        val paddingModifier  = Modifier.padding(10.dp)
        items(items = shoppingList, key = { item -> item.id }) { item ->
            Card(elevation = 10.dp, modifier = paddingModifier, onClick = {}) {
                Row(horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = item.name + ": " + item.created, modifier = paddingModifier)
                    TextButton({ viewModel.crossItOff(item.id, list_id, shoppingList) }
                    ) {
                        Icon(Icons.Default.Delete, null)
                    }
                }
            }
        }
        item{
            Button(onClick = {
                navController.popBackStack()
                navController.navigate("addItem/${list_id}")
            }) {
                Text(text = "Добавить товар")
            }
        }
    }
}