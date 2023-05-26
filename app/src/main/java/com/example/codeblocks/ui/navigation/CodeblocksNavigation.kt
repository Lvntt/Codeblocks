package com.example.codeblocks.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.codeblocks.ui.view.screens.ExpressionAdditionScreen
import com.example.codeblocks.ui.view.screens.OverviewScreen
import com.example.codeblocks.ui.view.screens.SavedProgramsScreen
import com.example.codeblocks.ui.view.screens.editor.EditorScreen
import org.koin.androidx.compose.koinViewModel

object CodeblocksDestinations {
    const val EDITOR_ROUTE = "editor"
    const val CONSOLE_ROUTE = "console"
    const val OVERVIEW_ROUTE = "overview"
    const val BLOCKS_ADDITION_ROUTE = "blocks_addition"
    const val EXPRESSION_ADDITION_ROUTE = "expression_addition"
    const val BLOCKS_WITHOUT_NESTING_ADDITION_ROUTE = "blocks_without_nesting_addition"
    const val SAVED_PROGRAMS_ROUTE = "saved_programs"
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
        composable(CodeblocksDestinations.BLOCKS_WITHOUT_NESTING_ADDITION_ROUTE) {
            BlocksAdditionScreen(
                availableBlocks = AvailableBlocks.availableBlocksWithoutNesting,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(CodeblocksDestinations.SAVED_PROGRAMS_ROUTE) {
            SavedProgramsScreen(
                navController = navController,
                savedPrograms = viewModel.getSavedPrograms(LocalContext.current),
                onProgramClick = viewModel::openSavedProgram
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
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                        contentDescription = item.name,
                    )
                }
            )
        }
    }
}