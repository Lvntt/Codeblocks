package com.example.codeblocks.ui.view.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.EndIfBlockWidth
import com.example.codeblocks.ui.theme.NestingBlockPaddingInt
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.AddBlock
import com.example.codeblocks.ui.view.blocks.IfExpressionBlock
import com.example.codeblocks.ui.view.blocks.InputFromConsoleBlock
import com.example.codeblocks.ui.view.blocks.OutputToConsoleBlock
import com.example.codeblocks.ui.view.blocks.StartBlock
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun EditorScreen(
    navController: NavController,
    viewModel: CodeEditorViewModel,
    modifier: Modifier = Modifier,
) {
    val blocks = viewModel.programBlocks

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

                nestedBlocks(
                    nestingLevel = 0,
                    navController = navController,
                    viewModel = viewModel,
                    nestedBlocks = blocks,
                    reorderableState = state
                )
            }
        }
    }
}

fun LazyListScope.nestedBlocks(
    nestingLevel: Int,
    navController: NavController,
    viewModel: CodeEditorViewModel,
    nestedBlocks: MutableList<BlockData>,
    reorderableState: ReorderableLazyListState
) {
    nestedBlocks.forEach { block ->
        block(
            nestingLevel = nestingLevel,
            navController = navController,
            viewModel = viewModel,
            block = block,
            reorderableState = reorderableState
        )
    }
    item {
        Row {
            Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
            AddBlock(
                onClick = {
                    viewModel.setAddBlockCallback {
                        nestedBlocks.add(viewModel.createBlockDataByType(it) as BlockData)
                    }
                    navController.navigate(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE)
                }
            )
        }
    }
}

fun LazyListScope.block(
    nestingLevel: Int,
    navController: NavController,
    viewModel: CodeEditorViewModel,
    block: BlockData,
    reorderableState: ReorderableLazyListState
) {
    item(contentType = block.id, key = block.id) {
        // TODO to view/common
        ReorderableItem(
            reorderableState = reorderableState,
            key = block.id
        ) { isDragging ->
            val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
            Row(
                modifier = Modifier
                    .detectReorderAfterLongPress(reorderableState)
                    .shadow(elevation.value, BlockElementShape)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
                when (block.blockClass) {
                    CreateVariableBlock::class -> {
                        VariableDeclarationBlock(
                            parameters = block.blockParametersData as VariableDeclarationBlockParameters
                        )
                    }
                    SetVariableBlock::class -> {
                        VariableAssignmentBlock(
                            navController = navController,
                            setAddBlockCallback = viewModel::setAddBlockCallback,
                            createBlockDataByType = viewModel::createBlockDataByType,
                            parameters = block.blockParametersData as VariableAssignmentBlockParameters,
                            isEditable = true
                        )
                    }
                    PrintToConsoleBlock::class -> {
                        OutputToConsoleBlock(
                            navController = navController,
                            setAddBlockCallback = viewModel::setAddBlockCallback,
                            createBlockDataByType = viewModel::createBlockDataByType,
                            parameters = block.blockParametersData as SingleExpressionParameter,
                            isEditable = true
                        )
                    }
                    ReadFromConsoleBlock::class -> {
                        InputFromConsoleBlock()
                    }
                    IfBlock::class -> {
                        IfExpressionBlock(
                            navController = navController,
                            setAddBlockCallback = viewModel::setAddBlockCallback,
                            createBlockDataByType = viewModel::createBlockDataByType,
                            parameters = block.blockParametersData as SingleExpressionParameter,
                            isEditable = true
                        )
                    }
                }
            }
        }
    }

    if (block is BlockWithNestingData) {
        nestedBlocks(
            nestingLevel = nestingLevel + 1,
            navController = navController,
            viewModel = viewModel,
            nestedBlocks = block.nestedBlocksData,
            reorderableState = reorderableState
        )
        item {
            Row {
                Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
                Box(modifier = Modifier
                    .height(BlockHeight)
                    .width(EndIfBlockWidth)
                    .clip(BlockElementShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(BlockPadding)) {
                }
            }
        }
    }
}