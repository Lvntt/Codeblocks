package com.example.codeblocks.ui.view.blocks

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.parameters.ThreeExpressionBlockParameters
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.NestingColor
import com.example.codeblocks.ui.theme.SpacerBetweenInnerElementsWidth
import com.example.codeblocks.ui.view.common.ComposableByExpressionBlockClass
import kotlin.reflect.KClass

@Composable
fun ThreeExpressionBlock(
    navController: NavController,
    modifier: Modifier = Modifier,
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit = {},
    createBlockDataByType: (KClass<out Block>) -> BlockData? = { null },
    parameters: ThreeExpressionBlockParameters = ThreeExpressionBlockParameters(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true,
    @StringRes startTextId: Int,
    @StringRes midTextId: Int,
    @StringRes endTextId: Int,
    isInBlockWithNesting: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val onAddFirstExpressionClick = {
        setAddBlockCallback {
            parameters.firstExpression = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }
    val onAddSecondExpressionClick = {
        setAddBlockCallback {
            parameters.secondExpression = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }
    val onAddThirdExpressionClick = {
        setAddBlockCallback {
            parameters.thirdExpression = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }

    val containerColor = if (isInBlockWithNesting) {
        NestingColor.Container.color
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    val onContainerColor = if (isInBlockWithNesting) {
        NestingColor.OnContainer.color
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
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
            .background(containerColor)
            .padding(BlockPadding)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = startTextId),
                color = onContainerColor,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            val firstExpression = parameters.firstExpression
            if (firstExpression == null) {
                AddExpressionBlock(
                    isInBlockWithNesting = isInBlockWithNesting,
                    isEditable = isEditable,
                    onClick = { onAddFirstExpressionClick() }
                )
            } else {
                ComposableByExpressionBlockClass(
                    isInBlockWithNesting = isInBlockWithNesting,
                    navController = navController,
                    parametersExpression = firstExpression,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType
                )
            }

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = midTextId),
                color = onContainerColor,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            val secondExpression = parameters.secondExpression
            if (secondExpression == null) {
                AddExpressionBlock(
                    isInBlockWithNesting = isInBlockWithNesting,
                    isEditable = isEditable,
                    onClick = { onAddSecondExpressionClick() }
                )
            } else {
                ComposableByExpressionBlockClass(
                    isInBlockWithNesting = isInBlockWithNesting,
                    navController = navController,
                    parametersExpression = secondExpression,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType
                )
            }

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = endTextId),
                color = onContainerColor,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            val thirdExpression = parameters.thirdExpression
            if (thirdExpression == null) {
                AddExpressionBlock(
                    isInBlockWithNesting = isInBlockWithNesting,
                    isEditable = isEditable,
                    onClick = { onAddThirdExpressionClick() }
                )
            } else {
                ComposableByExpressionBlockClass(
                    isInBlockWithNesting = isInBlockWithNesting,
                    navController = navController,
                    parametersExpression = thirdExpression,
                    setAddBlockCallback = setAddBlockCallback,
                    createBlockDataByType = createBlockDataByType
                )
            }
        }
    }
}