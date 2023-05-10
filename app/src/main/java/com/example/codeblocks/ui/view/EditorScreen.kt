package com.example.codeblocks.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codeblocks.presentation.CodeEditorViewModel
import com.example.codeblocks.ui.CodeblocksDestinations
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditorScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CodeEditorViewModel = koinViewModel()
) {
    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Column(
                modifier = modifier.fillMaxSize(),
            ) {
                StartBlock()
                AddBlock(
                    onClick = {
                        navController.navigate(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE)
                        viewModel.onAddBlockClick()
                    }
                )
            }
        }
    }
}