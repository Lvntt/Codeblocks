package com.example.codeblocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.BottomNavItems
import com.example.codeblocks.ui.navigation.BottomNavigationBar
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.navigation.Navigation
import com.example.codeblocks.ui.theme.CodeblocksTheme
import com.example.codeblocks.ui.theme.ConsoleBackground
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CodeblocksTheme {
                val viewModel = koinViewModel<CodeEditorViewModel>()
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry.value?.destination?.route
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            buttons = BottomNavItems.getBottomNavItems(),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    },
                    floatingActionButton = {
                        when (currentRoute) {
                            CodeblocksDestinations.EDITOR_ROUTE -> {
                                ExtendedFloatingActionButton(
                                    onClick = {
                                        viewModel.runProgram()
                                        navController.navigate(CodeblocksDestinations.CONSOLE_ROUTE)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = getString(R.string.run)
                                        )
                                    },
                                    text = {
                                        Text(text = getString(R.string.run))
                                    }
                                )
                            }

                            CodeblocksDestinations.BLOCKS_ADDITION_ROUTE,
                            CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE -> {
                                ExtendedFloatingActionButton(
                                    onClick = {
                                        navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = getString(R.string.backToEditor)
                                        )
                                    },
                                    text = {
                                        Text(text = getString(R.string.backToEditor))
                                    }
                                )
                            }

                            else -> {
                                return@Scaffold
                            }
                        }
                    }
                ) { innerPadding ->
                    val systemUiController = rememberSystemUiController()
                    val defaultBackground = MaterialTheme.colorScheme.background
                    val color = remember { Animatable(defaultBackground) }

                    LaunchedEffect(currentRoute) {
                        when (currentRoute) {
                            CodeblocksDestinations.CONSOLE_ROUTE -> color.animateTo(ConsoleBackground)

                            else -> color.animateTo(defaultBackground)
                        }
                    }
                    SideEffect {
                        systemUiController.setStatusBarColor(color.value)
                    }

                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color.value)
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }

}