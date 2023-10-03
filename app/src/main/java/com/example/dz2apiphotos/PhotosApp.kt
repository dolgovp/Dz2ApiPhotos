package com.example.dz2apiphotos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dz2apiphotos.ui.theme.AddItem
import com.example.dz2apiphotos.ui.theme.AddList
import com.example.dz2apiphotos.ui.theme.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
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
            val navController = rememberNavController()
            val homeViewModel: HomeViewModel =
                viewModel(factory = HomeViewModel.Factory)
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(viewModel = homeViewModel, modifier = Modifier, navController ) }
                composable("selectedItem/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    SelectedList(backStackEntry.arguments?.getInt("userId")!!, homeViewModel, navController)
                }
                composable("addItem/{list_id}",
                    arguments = listOf((navArgument("list_id") { type = NavType.IntType }),)
                ) { backStackEntry ->
                    AddItem(backStackEntry.arguments?.getInt("list_id")!!,
                        homeViewModel, navController)
                }
                composable("addList",) {
                    AddList(homeViewModel, navController)
                }
            }
        }
    }
}