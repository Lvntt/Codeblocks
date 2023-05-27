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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.loop.DoWhileBlock
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.BlockWithNestingData
import com.example.codeblocks.presentation.block.parameters.IfBlockParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.reorderable.ReorderableItem
import com.example.codeblocks.reorderable.ReorderableLazyListState
import com.example.codeblocks.reorderable.detectReorderAfterLongPress
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockDraggingElevation
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.DefaultBlockElevation
import com.example.codeblocks.ui.theme.EndIfBlockWidth
import com.example.codeblocks.ui.theme.NestingBlockPaddingInt
import com.example.codeblocks.ui.theme.NestingColor
import com.example.codeblocks.ui.view.blocks.AddBlock
import com.example.codeblocks.ui.view.blocks.SingleTextBlockView
import com.example.codeblocks.ui.view.blocks.WhileLoopBlock
import com.example.codeblocks.ui.view.common.BlockView
import java.util.UUID

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.nestedBlocks(
    nestingLevel: Int = 0,
    navController: NavController,
    viewModel: CodeEditorViewModel,
    addBlockId: UUID? = null,
    parentBlockId: UUID? = null,
    nestedBlocks: MutableList<BlockData>,
    reorderableState: ReorderableLazyListState,
    visible: Boolean = true
) {
    if (!visible) return

    val isDeleteMode = viewModel.isDeleteMode.value

    nestedBlocks.forEach { block ->
        block(
            nestingLevel = nestingLevel,
            navController = navController,
            viewModel = viewModel,
            block = block,
            isDeleteMode = isDeleteMode,
            reorderableState = reorderableState
        )
    }
    item(key = addBlockId) {
        Row(modifier = Modifier.animateItemPlacement()) {
            Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
            AddBlock(
                onClick = {
                    viewModel.setAddBlockCallback {
                        viewModel.addNewBlockToList(it, nestedBlocks, parentBlockId)
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
    isDeleteMode: Boolean,
    reorderableState: ReorderableLazyListState,
) {
    val onDeleteBlock: (UUID) -> Unit = {
        viewModel.removeBlockFromList(it)
    }

    item(
        contentType = block::class,
        key = block.id
    ) {
        ReorderableItem(
            reorderableState = reorderableState, key = block.id
        ) { isDragging ->
            val elevation = animateDpAsState(
                if (isDragging) {
                    BlockDraggingElevation
                } else {
                    DefaultBlockElevation
                }
            )
            val spacing by animateDpAsState(targetValue = (NestingBlockPaddingInt * nestingLevel).dp)

            Row {
                Spacer(modifier = Modifier.width(spacing))
                Box(
                    modifier = Modifier
                        .detectReorderAfterLongPress(
                            state = reorderableState,
                            onDragStartListener = {
                                if (block is BlockWithNestingData) {
                                    viewModel.setBlockExpanded(false, block)
                                }
                            },
                            onDragEndListener = {
                                if (block is BlockWithNestingData) {
                                    viewModel.setBlockExpanded(true, block)
                                }
                            })
                        .shadow(elevation.value, BlockElementShape)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    BlockView(
                        block = block,
                        navController = navController,
                        isDeleteMode = isDeleteMode,
                        onDeleteBlock = onDeleteBlock,
                        setAddBlockCallback = viewModel::setAddBlockCallback,
                        createBlockDataByType = viewModel::createBlockDataByType
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
        parentBlockId = block.id,
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

                IfBlock::class -> {
                    if ((block.blockParametersData as IfBlockParameters).elseBlock == null) {
                        AddBlock(
                            isInBlockWithNesting = true,
                            onClick = {
                                viewModel.addElseBlock(block)
                            },
                            labelId = R.string.addElseBranch
                        )
                    } else {
                        SingleTextBlockView(
                            isInBlockWithNesting = true,
                            descriptionStringRes = R.string.elseKeyword
                        )
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .height(BlockHeight)
                            .width(EndIfBlockWidth)
                            .clip(BlockElementShape)
                            .background(NestingColor.Container.color)
                            .padding(BlockPadding)
                    )
                }
            }
        }
    }

    if (block.blockClass == IfBlock::class) {
        val elseBlock = (block.blockParametersData as IfBlockParameters).elseBlock ?: return
        nestedBlocks(
            nestingLevel = nestingLevel + 1,
            navController = navController,
            viewModel = viewModel,
            addBlockId = elseBlock.addBlockButtonId,
            parentBlockId = elseBlock.id,
            nestedBlocks = elseBlock.nestedBlocksData,
            reorderableState = reorderableState,
            visible = block.expanded
        )
        item(key = elseBlock.bottomBorderId) {
            Row(modifier = Modifier.animateItemPlacement()) {
                Spacer(modifier = Modifier.width((NestingBlockPaddingInt * nestingLevel).dp))
                Box(
                    modifier = Modifier
                        .height(BlockHeight)
                        .width(EndIfBlockWidth)
                        .clip(BlockElementShape)
                        .background(NestingColor.Container.color)
                        .padding(BlockPadding)
                )
            }
        }
    }
}