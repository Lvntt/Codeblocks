package com.example.codeblocks.ui.view.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.parameters.ForLoopBlockParameters
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.NestingColor
import com.example.codeblocks.ui.theme.SpacerBetweenInnerElementsWidth
import com.example.codeblocks.ui.view.common.BlockView
import com.example.codeblocks.ui.view.common.ComposableByExpressionBlockClass
import kotlin.reflect.KClass

@Composable
fun ForLoopBlock(
    navController: NavController,
    modifier: Modifier = Modifier,
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit = {},
    createBlockDataByType: (KClass<out Block>) -> BlockData? = { null },
    parameters: ForLoopBlockParameters = ForLoopBlockParameters(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }

    val onAddExpressionClick = {
        setAddBlockCallback {
            parameters.expressionBlock = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }

    val onAddInitBlockClick = {
        setAddBlockCallback {
            parameters.initBlock = createBlockDataByType(it)
        }
        navController.navigate(CodeblocksDestinations.BLOCKS_WITHOUT_NESTING_ADDITION_ROUTE)
    }

    val onAddPostIterationBlockClick = {
        setAddBlockCallback {
            parameters.postIterationBlock = createBlockDataByType(it)
        }
        navController.navigate(CodeblocksDestinations.BLOCKS_WITHOUT_NESTING_ADDITION_ROUTE)
    }

    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (!isEditable) {
                    onAddBlockClick()
                }
            }
            .height(BlockHeight)
            .widthIn(BlockMinimumWidth, Dp.Infinity)
            .clip(BlockElementShape)
            .background(NestingColor.Container.color)
    ) {
        Row(
            modifier = modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = modifier.width(BlockPadding)
            )

            Text(
                text = stringResource(id = R.string.forKeyword),
                color = NestingColor.OnContainer.color,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = R.string.openingBracket),
                color = NestingColor.OnContainer.color,
                style = BlockRegularTextStyle
            )

            val initBlock = parameters.initBlock
            if (initBlock == null) {
                Spacer(
                    modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                )

                AddExpressionBlock(
                    isInBlockWithNesting = true,
                    isEditable = isEditable,
                    onClick = { onAddInitBlockClick() }
                )

                Spacer(
                    modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                )
            } else {
                BlockView(
                    isInBlockWithNesting = true,
                    block = initBlock,
                    navController = navController,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType
                )
            }

            Text(
                text = stringResource(id = R.string.semicolon),
                color = NestingColor.OnContainer.color,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            val parametersExpression = parameters.expressionBlock
            if (parametersExpression == null) {
                AddExpressionBlock(
                    isInBlockWithNesting = true,
                    isEditable = isEditable,
                    onClick = { onAddExpressionClick() }
                )
            } else {
                ComposableByExpressionBlockClass(
                    isInBlockWithNesting = true,
                    navController = navController,
                    parametersExpression = parametersExpression,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType
                )
            }

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = R.string.semicolon),
                color = NestingColor.OnContainer.color,
                style = BlockRegularTextStyle
            )

            val postIterationBlock = parameters.postIterationBlock
            if (postIterationBlock == null) {
                Spacer(
                    modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                )

                AddExpressionBlock(
                    isInBlockWithNesting = true,
                    isEditable = isEditable,
                    onClick = { onAddPostIterationBlockClick() }
                )

                Spacer(
                    modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                )
            } else {
                BlockView(
                    isInBlockWithNesting = true,
                    block = postIterationBlock,
                    navController = navController,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType
                )
            }

            Text(
                text = stringResource(id = R.string.closingBracket),
                color = NestingColor.OnContainer.color,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(BlockPadding)
            )
        }
    }
}