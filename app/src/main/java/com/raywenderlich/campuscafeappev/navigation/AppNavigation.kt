package com.raywenderlich.campuscafeappev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.raywenderlich.campuscafeappev.mainUI.HomeScreen
import com.raywenderlich.campuscafeappev.menu.MenuScreen

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val MENU_DETAILS = "menu/{itemId}"
}

@Composable
fun AppNavigation() {
     val navController = rememberNavController()

    NavHost(
        navController= navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(onMenuClick = {
                navController.navigate(Routes.MENU)
            }
            )
        }
        composable(Routes.MENU) {
            MenuScreen()
        }
    }
}