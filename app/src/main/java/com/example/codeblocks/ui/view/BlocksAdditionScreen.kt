package com.example.codeblocks.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.presentation.CodeEditorViewModel
import com.example.codeblocks.ui.AvailableBlocks.availableBlocks
import com.example.codeblocks.ui.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks

@Composable
fun BlocksAdditionScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CodeEditorViewModel
) {
    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(BlockPadding)
    ) {
        item {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(PaddingBetweenBlocks)
            ) {
                items(availableBlocks) { currentBlockClass ->
                    val onClick = {
                        viewModel.onAddBlockClick(currentBlockClass)
                        navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
                    }
                    when (currentBlockClass) {
                        CreateVariableBlock::class -> {
                            VariableDeclarationBlock(
                                onClick = onClick,
                                isEditable = false
                            )
                        }
                    }
                }
            }
        }
    }
}