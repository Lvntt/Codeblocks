package com.example.codeblocks.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.AvailableBlocks
import com.example.codeblocks.ui.view.screens.BlocksAdditionScreen
import com.example.codeblocks.ui.view.screens.ConsoleScreen
import com.example.codeblocks.ui.view.screens.EditorScreen
import com.example.codeblocks.ui.view.screens.ExpressionAdditionScreen
import com.example.codeblocks.ui.view.screens.OverviewScreen
import org.koin.androidx.compose.koinViewModel

object CodeblocksDestinations {
    const val EDITOR_ROUTE = "editor"
    const val CONSOLE_ROUTE = "console"
    const val OVERVIEW_ROUTE = "overview"
    const val BLOCKS_ADDITION_ROUTE = "blocks_addition"
    const val EXPRESSION_ADDITION_ROUTE = "expression_addition"
}

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: CodeEditorViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = CodeblocksDestinations.EDITOR_ROUTE
    ) {
        composable(CodeblocksDestinations.EDITOR_ROUTE) {
            EditorScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(CodeblocksDestinations.CONSOLE_ROUTE) {
            ConsoleScreen()
        }
        composable(CodeblocksDestinations.OVERVIEW_ROUTE) {
            OverviewScreen()
        }
        composable(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE) {
            BlocksAdditionScreen(
                availableBlocks = AvailableBlocks.availableBlocks,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE) {
            ExpressionAdditionScreen(
                availableExpressions = AvailableBlocks.availableExpressions,
                navController = navController,
                viewModel = viewModel
            )
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
        containerColor = Color.LightGray
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