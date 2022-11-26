package com.example.dz2apiphotos

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PhotosApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val homeViewModel: HomeViewModel =
                viewModel(factory = HomeViewModel.Factory)
            HomeScreen(
                homeViewModel = homeViewModel
            )
        }
    }
}