package com.example.codeblocks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codeblocks.ui.BottomNavItems
import com.example.codeblocks.ui.BottomNavigationBar
import com.example.codeblocks.ui.CodeblocksDestinations
import com.example.codeblocks.ui.Navigation
import com.example.codeblocks.ui.theme.CodeblocksTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            CodeblocksTheme {
                val navController = rememberNavController()
                var fabClicked by remember { mutableStateOf(false) }
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry.value?.destination?.route

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            buttons = BottomNavItems.getBottomNavItems(),
                            navController = navController,
                            onItemClick = {
                                fabClicked = false
                                navController.navigate(it.route)
                            }
                        )
                    },
                    floatingActionButton = {
                        if (currentRoute != CodeblocksDestinations.EDITOR_ROUTE &&
                            currentRoute != CodeblocksDestinations.BLOCKS_ADDITION_ROUTE) {
                            return@Scaffold
                        }
                        ExtendedFloatingActionButton(
                            onClick = {
                                if (fabClicked) {
                                    navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
                                } else {
                                    navController.navigate(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE)
                                }
                                fabClicked = !fabClicked
                            },
                            icon = {
                                if (fabClicked) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = getString(R.string.backToEditor)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = getString(R.string.addBlocks)
                                    )
                                }
                            },
                            text = {
                                if (fabClicked) {
                                    Text(text = getString(R.string.backToEditor))
                                } else {
                                    Text(text = getString(R.string.addBlocks))
                                }
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
    
}