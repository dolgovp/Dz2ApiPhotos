package com.example.dz2apiphotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dz2apiphotos.model.Item
import com.example.dz2apiphotos.model.ListItem

@Composable
fun SelectedList(
    list_id: Int,
    homeViewModel: HomeViewModel,
    navController: NavController
){
    val state = homeViewModel.currentListState.collectAsState().value
    when (state) {
        is ShoppingUiState.Loading -> LoadingScreen(modifier = Modifier)
        is ShoppingUiState.Success -> {PhotosGridScreen(state.shoppingList.item_list, modifier = Modifier, navController, homeViewModel, list_id)}
        is ShoppingUiState.Error -> ErrorScreen(modifier = Modifier, homeViewModel.getShoppingList(list_id))
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotosGridScreen(shoppingList: List<ListItem>, modifier: Modifier = Modifier, navController: NavController, viewModel: HomeViewModel, list_id: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        val paddingModifier  = Modifier.padding(10.dp)
        items(items = shoppingList, key = { item -> item.id }) { item ->
            Card(elevation = 10.dp, modifier = paddingModifier, onClick = {viewModel.crossItOff(item.id)
                viewModel.getShoppingList(list_id)
            }) {
                Text(text = item.name+item.id, modifier = paddingModifier)
            }
        }
    }
}