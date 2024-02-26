package com.josemaxp.birdpedy.ui.navigation

sealed class AppScreens(val route: String) {
    data object MainScreen: AppScreens("mainScreen")
    data object MapScreen: AppScreens("mapScreen")
    data object BirdScreen: AppScreens("birdScreen")
}