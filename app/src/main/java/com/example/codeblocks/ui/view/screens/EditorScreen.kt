package com.example.codeblocks.ui.view.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.AddBlock
import com.example.codeblocks.ui.view.blocks.StartBlock
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun EditorScreen(
    navController: NavController,
    viewModel: CodeEditorViewModel,
    modifier: Modifier = Modifier
) {
    val blocks = remember { mutableStateOf(viewModel.programBlocks) }

    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = BlockPadding)
    ) {
        item {
            val state = rememberReorderableLazyListState(onMove = viewModel::moveBlock)

            LazyColumn(
                state = state.listState,
                modifier = modifier
                    .fillMaxSize()
                    .reorderable(state),
                verticalArrangement = Arrangement.spacedBy(PaddingBetweenBlocks),
                contentPadding = PaddingValues(vertical = BlockPadding)
            ) {
                item {
                    StartBlock()
                }

                items(blocks.value, { it.id }) { currentBlock ->
                    ReorderableItem(
                        reorderableState = state,
                        key = currentBlock.id
                    ) { isDragging ->
                        val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                        Column(
                            modifier = modifier
                                .detectReorderAfterLongPress(state)
                                .shadow(elevation.value, BlockElementShape)
                                .background(MaterialTheme.colorScheme.surface)
                        ) {
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