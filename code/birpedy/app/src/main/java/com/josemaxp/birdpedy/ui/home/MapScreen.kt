package com.josemaxp.birdpedy.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.josemaxp.birdpedy.ui.navigation.AppScreens
import com.josemaxp.birdpedy.ui.components.CustomizeBottomMenu
import com.josemaxp.birdpedy.ui.components.CustomizeMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavHostController, selected: MutableIntState) {

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Mapa de avistamientos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.background,
                    actionIconContentColor = MaterialTheme.colorScheme.background,
                )
            )
        }

    ){paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ){
            CustomizeMap(
                modifier = Modifier
                    .weight(0.9f)
            )

            CustomizeBottomMenu(
                modifier = Modifier
                    .weight(0.1f)
                    .height(75.dp),
                onClick0 = {
                    navController.navigate(route = AppScreens.MainScreen.route)
                },
                onClick1 = {
                    navController.navigate(route = AppScreens.MapScreen.route)
                },
                selected = selected
            )
        }

    }
}

