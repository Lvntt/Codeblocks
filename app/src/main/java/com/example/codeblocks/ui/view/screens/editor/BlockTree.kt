package com.example.codeblocks.ui.view.screens.editor

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionDeclaratorBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionReturnBlock
import com.example.codeblocks.domain.entity.blocks.loop.BreakBlock
import com.example.codeblocks.domain.entity.blocks.loop.ContinueBlock
import com.example.codeblocks.domain.entity.blocks.loop.DoWhileBlock
import com.example.codeblocks.domain.entity.blocks.loop.WhileBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.parameters.FunctionDeclarationParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.reorderable.ReorderableItem
import com.example.codeblocks.reorderable.ReorderableLazyListState
import com.example.codeblocks.reorderable.detectReorderAfterLongPress
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.EndIfBlockWidth
import com.example.codeblocks.ui.theme.NestingBlockPaddingInt
import com.example.codeblocks.ui.view.blocks.AddBlock
import com.example.codeblocks.ui.view.blocks.FunctionDeclarationBlock
import com.example.codeblocks.ui.view.blocks.SingleTextBlockView
import com.example.codeblocks.ui.view.blocks.IfExpressionBlock
import com.example.codeblocks.ui.view.blocks.InputFromConsoleBlock
import com.example.codeblocks.ui.view.blocks.OutputToConsoleBlock
import com.example.codeblocks.ui.view.blocks.ReturnBlock
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock
import com.example.codeblocks.ui.view.blocks.WhileLoopBlock
import java.util.UUID

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.nestedBlocks(
    nestingLevel: Int = 0,
    navController: NavController,
    viewModel: CodeEditorViewModel,
    addBlockId: UUID? = null,
    nestedBlocks: MutableList<BlockData>,
    reorderableState: ReorderableLazyListState,
    visible: Boolean = true
) {
    if (!visible) return
    nestedBlocks.forEach { block ->
        block(
            nestingLevel = nestingLevel,
            navController = navController,
            viewModel = viewModel,
            block = block,
            reorderableState = reorderableState
        )
    }
    item(key = addBlockId) {
        Row(modifier = Modifier.animateItemPlacement()) {
            Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
            AddBlock(onClick = {
                viewModel.setAddBlockCallback {
                    viewModel.addNewBlockToList(it, nestedBlocks)
                }
                navController.navigate(CodeblocksDestinations.BLOCKS_ADDITION_ROUTE)
            })
        }
    }
}

fun LazyListScope.block(
    nestingLevel: Int,
    navController: NavController,
    viewModel: CodeEditorViewModel,
    block: BlockData,
    reorderableState: ReorderableLazyListState,
) {
    item(contentType = block::class, key = block.id) {
        ReorderableItem(
            reorderableState = reorderableState, key = block.id
        ) { isDragging ->
            val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
            val spacing by animateDpAsState(targetValue = (NestingBlockPaddingInt * nestingLevel).dp)
            Row {
                Spacer(modifier = Modifier.width(spacing))
                Box(
                    modifier = Modifier
                        .detectReorderAfterLongPress(
                            state = reorderableState,
                            onDragStartListener = {
                                if (block is BlockWithNestingData) {
                                    block.expanded = false
                                    block.parentBlockList?.removeAt(block.parentBlockListIndex)
                                    block.parentBlockList?.add(
                                        block.parentBlockListIndex, block
                                    )
                                }
                            },
                            onDragEndListener = {
                                if (block is BlockWithNestingData) {
                                    block.expanded = true
                                    block.parentBlockList?.removeAt(block.parentBlockListIndex)
                                    block.parentBlockList?.add(
                                        block.parentBlockListIndex, block
                                    )
                                }
                            })
                        .shadow(elevation.value, BlockElementShape)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    BlockView(
                        block = block, navController = navController, viewModel = viewModel
                    )
                }
            }
        }
    }

    if (block is BlockWithNestingData) {
        blockWithNestingBottomPart(
            nestingLevel = nestingLevel,
            navController = navController,
            viewModel = viewModel,
            block = block,
            reorderableState = reorderableState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.blockWithNestingBottomPart(
    nestingLevel: Int,
    navController: NavController,
    viewModel: CodeEditorViewModel,
    block: BlockWithNestingData,
    reorderableState: ReorderableLazyListState
) {
    nestedBlocks(
        nestingLevel = nestingLevel + 1,
        navController = navController,
        viewModel = viewModel,
        addBlockId = block.addBlockButtonId,
        nestedBlocks = block.nestedBlocksData,
        reorderableState = reorderableState,
        visible = block.expanded
    )
    if (!block.expanded) return
    item(key = block.bottomBorderId) {
        Row(modifier = Modifier.animateItemPlacement()) {
            Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
            when (block.blockClass) {
                DoWhileBlock::class -> {
                    WhileLoopBlock(
                        navController = navController,
                        setAddBlockCallback = viewModel::setAddBlockCallback,
                        createBlockDataByType = viewModel::createBlockDataByType,
                        parameters = block.blockParametersData as SingleExpressionParameter,
                        isEditable = true
                    )
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .height(BlockHeight)
                            .width(EndIfBlockWidth)
                            .clip(BlockElementShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(BlockPadding)
                    ) {}
                }
            }
        }
    }
}

@Composable
private fun BlockView(
    block: BlockData, navController: NavController, viewModel: CodeEditorViewModel
) {
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

        WhileBlock::class -> {
            WhileLoopBlock(
                navController = navController,
                setAddBlockCallback = viewModel::setAddBlockCallback,
                createBlockDataByType = viewModel::createBlockDataByType,
                parameters = block.blockParametersData as SingleExpressionParameter,
                isEditable = true
            )
        }

        DoWhileBlock::class -> {
            SingleTextBlockView(
                descriptionStringRes = R.string.doKeyword
            )
        }

        BreakBlock::class -> {
            SingleTextBlockView(
                descriptionStringRes = R.string.breakKeyword
            )
        }

        ContinueBlock::class -> {
            SingleTextBlockView(
                descriptionStringRes = R.string.continueKeyword
            )
        }

        FunctionReturnBlock::class -> {
            ReturnBlock(
                navController = navController,
                setAddBlockCallback = viewModel::setAddBlockCallback,
                createBlockDataByType = viewModel::createBlockDataByType,
                parameters = block.blockParametersData as SingleExpressionParameter,
                isEditable = true
            )
        }

        FunctionDeclaratorBlock::class -> {
            FunctionDeclarationBlock(
                parameters = block.blockParametersData as FunctionDeclarationParameters
            )
        }
    }
}