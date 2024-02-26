package com.josemaxp.birdpedy.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatchViewModel
import com.josemaxp.birdpedy.dao.birds.Bird
import com.josemaxp.birdpedy.dao.birds.BirdViewModel
import com.josemaxp.birdpedy.dao.families.FamilyViewModel
import com.josemaxp.birdpedy.dao.order.OrderViewModel
import com.josemaxp.birdpedy.model.loadData
import com.josemaxp.birdpedy.ui.birdscreen.BirdScreen
import com.josemaxp.birdpedy.ui.home.MapScreen
import com.josemaxp.birdpedy.ui.home.PokedexScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    models: Map<String, AndroidViewModel>
) {
    val navController = rememberNavController()
    val loadDataControl = remember { mutableStateOf( false) }
    val birdViewModel = models["bird"] as BirdViewModel
    val birdWatchViewModel = models["birdWatch"] as BirdWatchViewModel
    val familyViewModel = models["family"] as FamilyViewModel
    val orderViewModel = models["order"] as OrderViewModel
    val allBirdsWatched = birdWatchViewModel.allValues.collectAsState(initial = emptyList()).value
    val allBirds = birdViewModel.allValues.collectAsState(initial = emptyList())
    val selectedBird = birdViewModel.selectedBird.observeAsState(initial = birdViewModel.basicData()).value


    //OnLoad(loadDataControl, birdViewModel, allBirds)

    //Posición de la pantalla
    val selected = remember { mutableIntStateOf(0) }

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.MainScreen.route){
            PokedexScreen(navController, birdViewModel, birdWatchViewModel, allBirdsWatched, allBirds, selected)
        }
        composable(route = AppScreens.MapScreen.route){
            MapScreen(navController, selected)
        }
        composable(route = AppScreens.BirdScreen.route){
            BirdScreen(navController, selectedBird, familyViewModel, orderViewModel)
        }
    }
}

@Composable
fun OnLoad(
    loadDataControl: MutableState<Boolean>,
    birdViewModel: BirdViewModel,
    allBirds: State<List<Bird>>
) {
    if(!loadDataControl.value) {
        loadData(birdViewModel, allBirds, loadDataControl)
    }
}