package com.raywenderlich.campuscafeappev.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.raywenderlich.campuscafeappev.mainUI.HomeScreen
import com.raywenderlich.campuscafeappev.menu.MenuScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.raywenderlich.campuscafeappev.menu.MenuDetailsScreen
import com.raywenderlich.campuscafeappev.order.OrderScreen
import com.raywenderlich.campuscafeappev.viewModel.CampusCafeViewModel
import com.raywenderlich.campuscafeappev.ui.theme.profile.ProfileScreen

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val MENU_DETAILS = "menu/{itemId}"
    const val ORDER = "order"
    const val PROFILE = "profile"
}

@Composable
fun AppNavigation() {
    // Navigation Controller manges the movement between screens.
     val navController = rememberNavController()

   // ViewModel Takes place of orderManager:
    val viewModel: CampusCafeViewModel = viewModel()
    // ORDER-ITEMS:
    val orderItems by viewModel.orderItems.collectAsStateWithLifecycle()
    // OBSERVE Total:
    val total by viewModel.total.collectAsStateWithLifecycle()
    // POINTS-TRACKER:
    val points by viewModel.points.collectAsStateWithLifecycle()

    // NavHost contains all the screens in the application.
    NavHost(
        navController= navController,
        startDestination = Routes.HOME
    ) {
        // HOMESCREEN:
        composable(Routes.HOME) {
            // Homescreen tells the application that the user wants to see/ view the menu.
            HomeScreen(
                points = points,
                onMenuClick = {
                    navController.navigate(Routes.MENU)
                },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }
        composable(Routes.MENU) {
            MenuScreen(
                // Give MenuScreen the menu data.
                menuItems = viewModel.menuItems,
                // When the user selects an item, navigate to the details screen.
                onItemClick = { item ->
                    navController.navigate(
                        "menu/${item.id}"
                    )
                }
            )
        }

        // Menu Details:
        composable(
            route = Routes.MENU_DETAILS,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry
                .arguments
                ?.getInt("itemId")

            val selectedItem = viewModel.menuItems.find {
                it.id == itemId
            }

            if (selectedItem != null) {

                MenuDetailsScreen(
                    // Give the selected menu item to the screen.
                    item = selectedItem,

                    onBackClick = {
                        // Remove the current screen from
                        // the navigation back stack.
                        navController.popBackStack()
                    },

                    onAddToOrder = {
                        // Add the selected item to the order.
                        viewModel.addItem(
                            selectedItem
                        )

                        // After adding the item,
                        // navigate to the order screen.
                        navController.navigate(
                            Routes.ORDER
                        )
                    }
                )
            }
        }
        // ORDER
        composable(Routes.ORDER) {
            OrderScreen(
                //Give OrderScreen the current order items.
                orderItems = orderItems,

                // Gives the OrderScreen the current total.
                total = total,

                // REMOVE The Selected Item:
                onRemoveItem = { orderItem ->
                    viewModel.removeItem(
                        orderItem.menuItem
                    )
                },
                onClearOrder = {
                    viewModel.clearOrder()
                },

                onCheckout = {
                    // AWARD POINTS:
                    viewModel.addPoints(total.toInt())

                    // CLEAR The Completed Order:
                    viewModel.clearOrder()

                    // RETURN TO HOMESCREEN:
                    navController.navigate(
                        Routes.HOME
                    ) {
                        popUpTo(Routes.HOME) {
                            inclusive = false
                        }
                    }
                }
            )
        }
        // PROFILE [Screen]
        composable(Routes.PROFILE) {
            ProfileScreen(
                points = points,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }




    }
}