package com.example.codeblocks.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.presentation.CodeEditorViewModel
import com.example.codeblocks.ui.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks

@Composable
fun EditorScreen(
    navController: NavController,
    viewModel: CodeEditorViewModel,
    modifier: Modifier = Modifier
) {
    val blocks: List<Block> by viewModel.programBlocks

    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = BlockPadding)
    ) {
        item {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(PaddingBetweenBlocks),
                contentPadding = PaddingValues(vertical = BlockPadding)
            ) {
                item {
                    StartBlock()
                }

                items(blocks) {currentBlock ->
                    when (currentBlock::class) {
                        CreateVariableBlock::class -> {
                            VariableDeclarationBlock()
                        }
                    }
                }

                item {
                    AddBlock(
                        onClick = {
                            navController.navigate(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE)
                        }
                    )
                }
            }
        }
    }
}