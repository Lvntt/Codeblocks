package com.example.codeblocks.ui.view.screens

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
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.AddBlock
import com.example.codeblocks.ui.view.blocks.StartBlock
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock

@Composable
fun EditorScreen(
    navController: NavController,
    viewModel: CodeEditorViewModel,
    modifier: Modifier = Modifier
) {
    val blocks: List<BlockData> = viewModel.programBlocks

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

                items(blocks) { currentBlock ->
                    when (currentBlock.blockClass) {
                        CreateVariableBlock::class -> {
                                VariableDeclarationBlock(
                                    parameters = currentBlock.blockParametersData as VariableDeclarationBlockParameters
                            )
                        }
                        SetVariableBlock::class -> {
                            VariableAssignmentBlock(
                                navController = navController,
                                setAddBlockCallback = viewModel::setAddBlockCallback,
                                createBlockDataByType = viewModel::createBlockDataByType,
                                parameters = currentBlock.blockParametersData as VariableAssignmentBlockParameters,
                                isEditable = true
                            )
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