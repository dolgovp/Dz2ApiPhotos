package com.example.dz2apiphotos.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dz2apiphotos.AllListsUiState
import com.example.dz2apiphotos.HomeViewModel
import com.example.dz2apiphotos.R
import com.example.dz2apiphotos.model.Item
import com.example.dz2apiphotos.model.ListItem
import com.example.dz2apiphotos.ui.theme.*


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier,
    navController: NavController
) {
    val state = viewModel.allListsState.collectAsState().value
    when (state) {
        is AllListsUiState.Loading -> LoadingScreen(modifier)
        is AllListsUiState.Success -> {
            ListsGridScreen(state.shoppingList.shop_list, modifier, navController, viewModel)
        }
        is AllListsUiState.Error -> ErrorScreen(modifier, viewModel.getAllMyShopLists(viewModel.authKey))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier,retryAction: Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.loading_failed))
        Button(onClick = {retryAction }) {
            Text(stringResource(R.string.retry))
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListsGridScreen(shoppingList: List<Item>, modifier: Modifier = Modifier, navController: NavController, viewModel: HomeViewModel) {
    val configuration = LocalConfiguration.current
    var cellsNumber = 1
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
        cellsNumber = 2
    }
    //Проверить при разной ориентации экрана
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellsNumber),
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        item{
            Text(modifier = Modifier.padding(4.dp), fontStyle = MaterialTheme.typography.h4.fontStyle ,text = "Мои списки покупок")
        }
        val paddingModifier  = Modifier.padding(4.dp)
        items(items = shoppingList, key = { item -> item.id }) { item ->
            Card(elevation = 10.dp, modifier = paddingModifier, onClick = {viewModel.getShoppingList(item.id)
                navController.navigate("selectedItem/${item.id}")
            }){
                Row(horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = item.name, modifier = paddingModifier)
                    TextButton({viewModel.removeShoppingList(item.id)
                        //  здесь нужно попробовать обновить стейт без нового запроса в сеть
                        viewModel.getAllMyShopLists(viewModel.authKey)}
                    ) {
                        Icon(Icons.Default.Delete, null)
                    }
                }
            }

        }
        item{
            Button(onClick = {
                navController.popBackStack()
                navController.navigate("addList")})
            {
                Text("Новый список")
            }
        }
    }
}




