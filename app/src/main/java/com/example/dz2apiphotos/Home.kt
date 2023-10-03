package com.example.dz2apiphotos

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dz2apiphotos.model.Item
import com.example.dz2apiphotos.model.ListItem
import com.example.dz2apiphotos.model.ShoppingList
import com.example.dz2apiphotos.ui.theme.*
import kotlinx.datetime.LocalDate



@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier,
    navController: NavController
) {
    val state = viewModel.allListsState.collectAsState().value
    when (state) {
        is AllListsUiState.Loading -> LoadingScreen(modifier)
        is AllListsUiState.Success -> {ListsGridScreen(state.shoppingList.shop_list, modifier, navController, viewModel)}
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
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        val paddingModifier  = Modifier.padding(10.dp)
        items(items = shoppingList, key = { item -> item.id }) { item ->
            Card(elevation = 10.dp, modifier = paddingModifier, onClick = {viewModel.getShoppingList(item.id)
                navController.navigate("selectedItem/${item.id}")
            }) {
                Text(text = item.name+item.id, modifier = paddingModifier)
            }
        }
    }
}

@Composable
fun ListGridScreen(shoppingList: List<ListItem>, modifier: Modifier = Modifier) {

}


