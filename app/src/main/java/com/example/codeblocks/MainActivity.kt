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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codeblocks.presentation.model.FabMenuItem
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.reorderable.rememberReorderableLazyListState
import com.example.codeblocks.ui.fab.FabMenuButton
import com.example.codeblocks.ui.navigation.BottomNavItems
import com.example.codeblocks.ui.navigation.BottomNavigationBar
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.navigation.Navigation
import com.example.codeblocks.ui.theme.CodeblocksTheme
import com.example.codeblocks.ui.theme.ConsoleBackground
import com.example.codeblocks.ui.theme.EnterAnimationDensity
import com.example.codeblocks.ui.theme.FabMenuOffset
import com.example.codeblocks.ui.theme.FabMenuSpaceBetweenItems
import com.example.codeblocks.ui.theme.FadeInInitialAlpha
import com.example.codeblocks.ui.theme.FadeOutTweenDurationMillis
import com.example.codeblocks.ui.theme.SnackbarPadding
import com.example.codeblocks.ui.theme.SnackbarTextStyle
import com.example.codeblocks.ui.view.dialog.SaveFileDialog
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CodeblocksTheme {
                val viewModel = koinViewModel<CodeEditorViewModel>()
                val listState = rememberReorderableLazyListState(onMove = viewModel::moveBlock)
                val rowState = rememberLazyListState()
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry.value?.destination?.route
                val context = LocalContext.current
                var isFabExpanded by remember { mutableStateOf(false) }
                var saveFileDialogOpened by remember { mutableStateOf(false) }
                var snackbarVisibleState by remember { mutableStateOf(false) }

                if (saveFileDialogOpened) {
                    SaveFileDialog(
                        onDismiss = {
                            saveFileDialogOpened = false
                        },
                        onSave = { filename ->
                            viewModel.saveProgram(filename, context)
                            saveFileDialogOpened = false
                        }
                    )
                }

                Column {
                    AnimatedVisibility(
                        visible = snackbarVisibleState && currentRoute == CodeblocksDestinations.EDITOR_ROUTE
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Snackbar(
                                modifier = Modifier.padding(SnackbarPadding),
                                action = {
                                    Button(
                                        onClick = {
                                            snackbarVisibleState = false
                                        }
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.gotIt),
                                            style = SnackbarTextStyle
                                        )
                                    }
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.deleteModeSnackbarDescription),
                                    style = SnackbarTextStyle
                                )
                            }
                        }
                    }

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
                                            .offset(FabMenuOffset, FabMenuOffset),
                                        horizontalAlignment = Alignment.End,
                                        verticalArrangement = Arrangement.spacedBy(
                                            FabMenuSpaceBetweenItems,
                                            Alignment.Bottom
                                        )
                                    ) {
                                        AnimatedVisibility(
                                            visible = isFabExpanded,
                                            enter = slideInVertically {
                                                with(density) { EnterAnimationDensity.roundToPx() }
                                            } + expandVertically(
                                                expandFrom = Alignment.Top
                                            ) + fadeIn(
                                                initialAlpha = FadeInInitialAlpha
                                            ),
                                            exit = slideOutVertically(
                                                targetOffsetY = {
                                                    it / 2
                                                },
                                            ) + shrinkVertically() + fadeOut(
                                                animationSpec = tween(durationMillis = FadeOutTweenDurationMillis)
                                            )
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(FabMenuSpaceBetweenItems),
                                                horizontalAlignment = Alignment.End,
                                                verticalArrangement = Arrangement.spacedBy(
                                                    FabMenuSpaceBetweenItems,
                                                    Alignment.Bottom
                                                )
                                            ) {
                                                FabMenuButton(
                                                    fabMenuItem = FabMenuItem(
                                                        R.string.saveProgram,
                                                        Icons.Default.Add
                                                    ),
                                                    onClick = {
                                                        saveFileDialogOpened = true
                                                    }
                                                )
                                                FabMenuButton(
                                                    fabMenuItem = FabMenuItem(
                                                        R.string.deleteModeFabDescription,
                                                        Icons.Default.Delete
                                                    ),
                                                    onClick = {
                                                        snackbarVisibleState = !snackbarVisibleState
                                                        viewModel.changeDeleteMode()
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
                                                        navController.navigate(
                                                            CodeblocksDestinations.CONSOLE_ROUTE
                                                        )
                                                    }
                                                )
                                            }
                                        }
                                        Box(
                                            modifier = Modifier.offset(-FabMenuOffset, -FabMenuOffset)
                                        ) {
                                            ExtendedFloatingActionButton(
                                                onClick = {
                                                    if (viewModel.isDeleteMode.value) {
                                                        viewModel.changeDeleteMode()
                                                        snackbarVisibleState = false
                                                    } else {
                                                        isFabExpanded = !isFabExpanded
                                                    }
                                                },
                                                text = {
                                                    if (viewModel.isDeleteMode.value) {
                                                        Text(text = stringResource(R.string.exitDeleteMode))
                                                    } else {
                                                        Text(text = stringResource(R.string.manageFabDescription))
                                                    }
                                                },
                                                icon = {
                                                    if (viewModel.isDeleteMode.value) {
                                                        Icon(
                                                            imageVector = Icons.Default.ExitToApp,
                                                            contentDescription = getString(R.string.exitDeleteMode)
                                                        )
                                                    } else {
                                                        Icon(
                                                            imageVector = Icons.Default.Settings,
                                                            contentDescription = getString(R.string.manageFabDescription)
                                                        )
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }

                                CodeblocksDestinations.BLOCKS_ADDITION_ROUTE,
                                CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE,
                                CodeblocksDestinations.BLOCKS_WITHOUT_NESTING_ADDITION_ROUTE -> {
                                    ExtendedFloatingActionButton(
                                        onClick = {
                                            navController.navigate(CodeblocksDestinations.EDITOR_ROUTE) {
                                                popUpTo(CodeblocksDestinations.EDITOR_ROUTE) {
                                                    inclusive = true
                                                }
                                            }
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
                                CodeblocksDestinations.CONSOLE_ROUTE -> color.animateTo(
                                    ConsoleBackground
                                )

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
                            Navigation(
                                navController = navController,
                                editorListState = listState,
                                editorRowState = rowState
                            )
                        }
                    }
                }

            }
        }
    }

}