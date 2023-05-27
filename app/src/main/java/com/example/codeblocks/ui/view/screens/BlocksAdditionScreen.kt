package com.example.codeblocks.ui.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionCallBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionDeclaratorBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionReturnBlock
import com.example.codeblocks.domain.entity.blocks.loop.BreakBlock
import com.example.codeblocks.domain.entity.blocks.loop.ContinueBlock
import com.example.codeblocks.domain.entity.blocks.loop.DoWhileBlock
import com.example.codeblocks.domain.entity.blocks.loop.ForBlock
import com.example.codeblocks.domain.entity.blocks.loop.WhileBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.AddElementToListBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.CreateListBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.InsertListElementBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.RemoveElementFromListBlock
import com.example.codeblocks.domain.entity.blocks.variable.list.SetListElementBlock
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.ForLoopBlock
import com.example.codeblocks.ui.view.blocks.FunctionCallBlock
import com.example.codeblocks.ui.view.blocks.FunctionDeclarationBlock
import com.example.codeblocks.ui.view.blocks.IfExpressionBlock
import com.example.codeblocks.ui.view.blocks.OutputToConsoleBlock
import com.example.codeblocks.ui.view.blocks.ReturnBlock
import com.example.codeblocks.ui.view.blocks.SingleTextBlockView
import com.example.codeblocks.ui.view.blocks.ThreeExpressionBlock
import com.example.codeblocks.ui.view.blocks.TwoExpressionBlock
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock
import com.example.codeblocks.ui.view.blocks.WhileLoopBlock
import kotlin.reflect.KClass

@Composable
fun BlocksAdditionScreen(
    availableBlocks: List<KClass<out Block>>,
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
                        viewModel.executeCallback(currentBlockClass)
                        navController.navigate(CodeblocksDestinations.EDITOR_ROUTE)
                    }

                    when (currentBlockClass) {
                        CreateVariableBlock::class -> {
                            VariableDeclarationBlock(
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        SetVariableBlock::class -> {
                            VariableAssignmentBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        PrintToConsoleBlock::class -> {
                            OutputToConsoleBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        IfBlock::class -> {
                            IfExpressionBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        WhileBlock::class -> {
                            WhileLoopBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        DoWhileBlock::class -> {
                            SingleTextBlockView(
                                isInBlockWithNesting = true,
                                onAddBlockClick = onClick,
                                descriptionStringRes = R.string.doWhile,
                                isEditable = false
                            )
                        }

                        BreakBlock::class -> {
                            SingleTextBlockView(
                                onAddBlockClick = onClick,
                                descriptionStringRes = R.string.breakKeyword,
                                isEditable = false
                            )
                        }

                        ContinueBlock::class -> {
                            SingleTextBlockView(
                                onAddBlockClick = onClick,
                                descriptionStringRes = R.string.continueKeyword,
                                isEditable = false
                            )
                        }

                        FunctionReturnBlock::class -> {
                            ReturnBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        FunctionDeclaratorBlock::class -> {
                            FunctionDeclarationBlock(
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        FunctionCallBlock::class -> {
                            Box(
                                modifier = modifier
                                    .height(BlockHeight)
                                    .widthIn(BlockMinimumWidth, Dp.Infinity)
                                    .clip(BlockElementShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .padding(BlockPadding)
                            ) {
                                FunctionCallBlock(
                                    navController = navController,
                                    onAddBlockClick = onClick,
                                    isEditable = false
                                )
                            }
                        }

                        ForBlock::class -> {
                            ForLoopBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false
                            )
                        }

                        CreateListBlock::class -> {
                            VariableDeclarationBlock(
                                onAddBlockClick = onClick,
                                isEditable = false,
                                descriptionId = R.string.listType
                            )
                        }

                        AddElementToListBlock::class -> {
                            TwoExpressionBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false,
                                startTextId = R.string.add,
                                midTextId = R.string.to
                            )
                        }

                        RemoveElementFromListBlock::class -> {
                            TwoExpressionBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false,
                                startTextId = R.string.remove,
                                midTextId = R.string.elementFrom
                            )
                        }

                        SetListElementBlock::class -> {
                            ThreeExpressionBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false,
                                startTextId = R.string.set,
                                midTextId = R.string.setBlockMidPart,
                                endTextId = R.string.to,
                            )
                        }

                        InsertListElementBlock::class -> {
                            ThreeExpressionBlock(
                                navController = navController,
                                onAddBlockClick = onClick,
                                isEditable = false,
                                startTextId = R.string.insert,
                                midTextId = R.string.intoPos,
                                endTextId = R.string.of,
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = modifier.height(BlockHeight))
                }
            }
        }
    }
}