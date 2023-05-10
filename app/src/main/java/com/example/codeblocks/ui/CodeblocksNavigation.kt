package com.example.codeblocks.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.codeblocks.ui.view.BlocksAdditionScreen
import com.example.codeblocks.ui.view.ConsoleScreen
import com.example.codeblocks.ui.view.EditorScreen
import com.example.codeblocks.ui.view.OverviewScreen

object CodeblocksDestinations {
    const val EDITOR_ROUTE = "editor"
    const val CONSOLE_ROUTE = "console"
    const val OVERVIEW_ROUTE = "overview"
    const val BLOCKS_ADDITION_ROUTE = "blocks_addition"
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = CodeblocksDestinations.EDITOR_ROUTE
    ) {
        composable(CodeblocksDestinations.EDITOR_ROUTE) {
            EditorScreen(navController = navController)
        }
        composable(CodeblocksDestinations.CONSOLE_ROUTE) {
            ConsoleScreen()
        }
        composable(CodeblocksDestinations.OVERVIEW_ROUTE) {
            OverviewScreen()
        }
        composable(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE) {
            BlocksAdditionScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    buttons: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = Color.LightGray,
        tonalElevation = 5.dp
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = item.name
                    )
                }
            )
        }
    }
}