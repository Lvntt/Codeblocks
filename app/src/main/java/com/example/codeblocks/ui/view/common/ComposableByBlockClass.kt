package com.example.codeblocks.ui.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.conditional.IfBlock
import com.example.codeblocks.domain.entity.blocks.console.PrintToConsoleBlock
import com.example.codeblocks.domain.entity.blocks.console.ReadFromConsoleBlock
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
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.parameters.ForLoopBlockParameters
import com.example.codeblocks.presentation.block.parameters.FunctionCallParameters
import com.example.codeblocks.presentation.block.parameters.FunctionDeclarationParameters
import com.example.codeblocks.presentation.block.parameters.FunctionReturnParameters
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.presentation.block.parameters.VariableAssignmentBlockParameters
import com.example.codeblocks.presentation.block.parameters.VariableDeclarationBlockParameters
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.view.blocks.ForLoopBlock
import com.example.codeblocks.ui.view.blocks.FunctionCallBlock
import com.example.codeblocks.ui.view.blocks.FunctionDeclarationBlock
import com.example.codeblocks.ui.view.blocks.IfExpressionBlock
import com.example.codeblocks.ui.view.blocks.InputFromConsoleBlock
import com.example.codeblocks.ui.view.blocks.OutputToConsoleBlock
import com.example.codeblocks.ui.view.blocks.ReturnBlock
import com.example.codeblocks.ui.view.blocks.SingleTextBlockView
import com.example.codeblocks.ui.view.blocks.VariableAssignmentBlock
import com.example.codeblocks.ui.view.blocks.VariableDeclarationBlock
import com.example.codeblocks.ui.view.blocks.WhileLoopBlock
import java.util.UUID
import kotlin.reflect.KClass

@Composable
fun BlockView(
    block: BlockData,
    navController: NavController,
    isDeleteMode: Boolean = false,
    onDeleteBlock: (UUID) -> Unit = {},
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit,
    createBlockDataByType: (KClass<out Block>) -> BlockData?
) {

    when (block.blockClass) {
        CreateVariableBlock::class -> {
            VariableDeclarationBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                parameters = block.blockParametersData as VariableDeclarationBlockParameters,
            )
        }

        SetVariableBlock::class -> {
            VariableAssignmentBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = block.blockParametersData as VariableAssignmentBlockParameters,
            )
        }

        PrintToConsoleBlock::class -> {
            OutputToConsoleBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = block.blockParametersData as SingleExpressionParameter,
            )
        }

        ReadFromConsoleBlock::class -> {
            InputFromConsoleBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
            )
        }

        IfBlock::class -> {
            IfExpressionBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = block.blockParametersData as SingleExpressionParameter,
            )
        }

        WhileBlock::class -> {
            WhileLoopBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = block.blockParametersData as SingleExpressionParameter,
            )
        }

        DoWhileBlock::class -> {
            SingleTextBlockView(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                descriptionStringRes = R.string.doKeyword
            )
        }

        BreakBlock::class -> {
            SingleTextBlockView(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                descriptionStringRes = R.string.breakKeyword
            )
        }

        ContinueBlock::class -> {
            SingleTextBlockView(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                descriptionStringRes = R.string.continueKeyword
            )
        }

        FunctionReturnBlock::class -> {
            ReturnBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = block.blockParametersData as FunctionReturnParameters
            )
        }

        FunctionDeclaratorBlock::class -> {
            FunctionDeclarationBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                parameters = block.blockParametersData as FunctionDeclarationParameters
            )
        }

        FunctionCallBlock::class -> {
            Box(
                modifier = Modifier
                    .height(BlockHeight)
                    .widthIn(BlockMinimumWidth, Dp.Infinity)
                    .clip(BlockElementShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(BlockPadding)
            ) {
                FunctionCallBlock(
                    modifier = Modifier.deleteOnClick(
                        isDeleteMode = isDeleteMode,
                        onDeleteBlock = {
                            onDeleteBlock(block.id)
                        }
                    ),
                    navController = navController,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType,
                    parameters = block.blockParametersData as FunctionCallParameters
                )
            }
        }

        ForBlock::class -> {
            ForLoopBlock(
                modifier = Modifier.deleteOnClick(
                    isDeleteMode = isDeleteMode,
                    onDeleteBlock = {
                        onDeleteBlock(block.id)
                    }
                ),
                navController = navController,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType,
                parameters = block.blockParametersData as ForLoopBlockParameters,
            )
        }
    }
}

fun Modifier.deleteOnClick(isDeleteMode: Boolean, onDeleteBlock: () -> Unit) =
    this.then(
        if (isDeleteMode) {
            Modifier.pointerInput(Unit) {
                awaitPointerEventScope {
                    awaitFirstDown(requireUnconsumed = false)
                    onDeleteBlock()
                }
            }
        } else {
            Modifier
        }
    )