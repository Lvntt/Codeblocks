package com.example.codeblocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codeblocks.presentation.model.FabMenuItem
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.fab.FabMenuButton
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
                var isFabExpanded by remember { mutableStateOf(false) }
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
                                val density = LocalDensity.current
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .offset(16.dp, 16.dp),
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
                                ) {
                                    AnimatedVisibility(
                                        visible = isFabExpanded,
                                        enter = slideInVertically {
                                            with(density) { -40.dp.roundToPx() }
                                        } + expandVertically(
                                            expandFrom = Alignment.Top
                                        ) + fadeIn(
                                            initialAlpha = 0.3f
                                        ),
                                        exit = slideOutVertically(
                                            targetOffsetY = {
                                                it / 2
                                            },
                                        ) + shrinkVertically() + fadeOut(
                                            animationSpec = tween(durationMillis = 150)
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp),
                                            horizontalAlignment = Alignment.End,
                                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
                                        ) {
                                            FabMenuButton(
                                                fabMenuItem = FabMenuItem(
                                                    R.string.deleteModeFabDescription,
                                                    Icons.Default.Delete
                                                ),
                                                onClick = {
                                                    isFabExpanded = !isFabExpanded
                                                }
                                            )
                                            FabMenuButton(
                                                fabMenuItem = FabMenuItem(
                                                    R.string.runFabDescription,
                                                    Icons.Default.PlayArrow
                                                ),
                                                onClick = {
                                                    isFabExpanded = !isFabExpanded
                                                    viewModel.runProgram()
                                                    navController.navigate(CodeblocksDestinations.CONSOLE_ROUTE)
                                                }
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier.offset((-16).dp, (-16).dp)
                                    ) {
                                        ExtendedFloatingActionButton(
                                            onClick = {
                                                isFabExpanded = !isFabExpanded
                                            },
                                            text = { Text(text = stringResource(R.string.manageFabDescription)) },
                                            icon = {
                                                Icon(
                                                    imageVector = Icons.Default.Settings,
                                                    contentDescription = getString(R.string.manageFabDescription)
                                                )
                                            }
                                        )
                                    }
                                }
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