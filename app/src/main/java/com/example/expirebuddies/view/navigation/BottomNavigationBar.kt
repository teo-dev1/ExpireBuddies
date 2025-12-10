package com.example.expirebuddies.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavigationItem(
    val destination: Screen,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,

    )

val bottomNavBarItems = listOf(
    BottomNavigationItem(
        destination = Screen.Home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ), BottomNavigationItem(
        destination = Screen.Food,
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add
    ), BottomNavigationItem(
        destination = Screen.Profile,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
)


@Composable
fun BottomNavigationBar(rootNavController: NavHostController) {
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    NavigationBar {
        bottomNavBarItems.forEach { item ->
            val isSelected = item.destination.route == navBackStackEntry?.destination?.route

            NavigationBarItem(
                selected = isSelected,
                label = { Text(text = item.destination.route) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.destination.route
                    )
                },
                onClick = {
                    rootNavController.navigate(item.destination.route) {
                        popUpTo(rootNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

