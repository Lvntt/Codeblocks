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
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionDeclaratorBlock
import com.example.codeblocks.domain.entity.blocks.function.FunctionReturnBlock
import com.example.codeblocks.domain.entity.blocks.loop.BreakBlock
import com.example.codeblocks.domain.entity.blocks.loop.ContinueBlock
import com.example.codeblocks.domain.entity.blocks.loop.DoWhileBlock
import com.example.codeblocks.domain.entity.blocks.loop.WhileBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import com.example.codeblocks.presentation.viewmodel.CodeEditorViewModel
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.PaddingBetweenBlocks
import com.example.codeblocks.ui.view.blocks.FunctionDeclarationBlock
import com.example.codeblocks.ui.view.blocks.SingleTextBlockView
import com.example.codeblocks.ui.view.blocks.IfExpressionBlock
import com.example.codeblocks.ui.view.blocks.OutputToConsoleBlock
import com.example.codeblocks.ui.view.blocks.ReturnBlock
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
                    }
                }
            }
        }
    }
}