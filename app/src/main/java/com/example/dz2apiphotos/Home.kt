package com.example.dz2apiphotos

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.dz2apiphotos.model.Photo
import com.example.dz2apiphotos.ui.theme.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {

    val photos = homeViewModel.photos.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = COLUMN_H_PADDING.dp,
                vertical = COLUMN_V_PADDING.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (photos.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(photos)
            }
        }
        items(
            items = photos,
            key = { it.id }
        ) {
            RecipeRow(photo = it)
        }
        when (photos.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(photos)
            }
        }
    }
}

@Composable
private fun RecipeRow(
    photo: Photo?, modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
    Card(
        shape = RoundedCornerShape(CARD_CORNER.dp),
        modifier = modifier
            .fillMaxSize()
            .defaultMinSize(minWidth = CARD_DEFAULT_SIZE.dp, minHeight = CARD_DEFAULT_SIZE.dp),
        elevation = CARD_ELEVATION.dp
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo?.imgSrc)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.mars_photo),
            contentScale = ContentScale.Fit,
        )
    }
    Spacer(modifier = Modifier.height(SPACER_HEIGHT.dp))
}

private fun LazyListScope.Loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(PROGRESS_INDICATOR_PADDING.dp))
    }
}

private fun LazyListScope.Error(
     photos: LazyPagingItems<Photo>
) {
    item {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.loading_failed))
            Button(onClick = { photos.retry() }) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}
