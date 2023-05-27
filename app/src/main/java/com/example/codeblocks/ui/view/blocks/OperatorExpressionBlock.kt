package com.example.codeblocks.ui.view.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.parameters.TwoExpressionBlockParameters
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.NestingColor
import com.example.codeblocks.ui.theme.SpacerBetweenInnerElementsWidth
import com.example.codeblocks.ui.view.common.ComposableByExpressionBlockClass
import kotlin.reflect.KClass

@Composable
fun OperatorExpressionBlock(
    navController: NavController,
    blockOperator: String,
    modifier: Modifier = Modifier,
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit = {},
    createBlockDataByType: (KClass<out Block>) -> BlockData? = { null },
    parameters: TwoExpressionBlockParameters = TwoExpressionBlockParameters(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true,
    isInBlockWithNesting: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val onAddLeftOperandClick = {
        setAddBlockCallback {
            parameters.firstExpression = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }

    val onAddRightOperandClick = {
        setAddBlockCallback {
            parameters.secondExpression = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }

    val containerColor = if (isInBlockWithNesting) {
        NestingColor.Container.color
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (!isEditable) {
                    onAddBlockClick()
                }
            }
            .background(containerColor),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.openingBracket),
            style = BlockRegularTextStyle
        )

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        val parametersLeftOperandExpression = parameters.firstExpression
        if (parametersLeftOperandExpression == null) {
            AddExpressionBlock(
                isInBlockWithNesting = isInBlockWithNesting,
                isEditable = isEditable,
                onClick = { onAddLeftOperandClick() }
            )
        } else {
            ComposableByExpressionBlockClass(
                isInBlockWithNesting = isInBlockWithNesting,
                navController = navController,
                parametersExpression = parametersLeftOperandExpression,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        Text(
            text = blockOperator,
            style = BlockRegularTextStyle
        )

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        val parametersRightOperandExpression = parameters.secondExpression
        if (parametersRightOperandExpression == null) {
            AddExpressionBlock(
                isInBlockWithNesting = isInBlockWithNesting,
                isEditable = isEditable,
                onClick = { onAddRightOperandClick() }
            )
        } else {
            ComposableByExpressionBlockClass(
                isInBlockWithNesting = isInBlockWithNesting,
                navController = navController,
                parametersExpression = parametersRightOperandExpression,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )
        }

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        Text(
            text = stringResource(id = R.string.closingBracket),
            style = BlockRegularTextStyle
        )
    }
}