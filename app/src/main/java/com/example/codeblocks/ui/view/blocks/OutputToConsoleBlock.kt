package com.example.codeblocks.ui.view.blocks

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
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.data.BlockData
import com.example.codeblocks.presentation.block.data.ExpressionBlockData
import com.example.codeblocks.presentation.block.parameters.SingleExpressionParameter
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.SpacerBetweenInnerElementsWidth
import com.example.codeblocks.ui.view.common.ComposableByExpressionBlockClass
import kotlin.reflect.KClass

@Composable
fun OutputToConsoleBlock(
    navController: NavController,
    modifier: Modifier = Modifier,
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit = {},
    createBlockDataByType: (KClass<out Block>) -> BlockData? = { null },
    parameters: SingleExpressionParameter = SingleExpressionParameter(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val onAddExpressionClick = {
        setAddBlockCallback {
            parameters.expression = createBlockDataByType(it) as ExpressionBlockData
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
    }

    Box(
        modifier = modifier
            .height(BlockHeight)
            .widthIn(BlockMinimumWidth, Dp.Infinity)
            .clip(BlockElementShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(BlockPadding)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (!isEditable) {
                    onAddBlockClick()
                }
            }
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.print),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            val parametersExpression = parameters.expression
            if (parametersExpression == null) {
                AddExpressionBlock(
                    isEditable = isEditable,
                    onClick = { onAddExpressionClick() }
                )
            } else {
                ComposableByExpressionBlockClass(
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
                text = stringResource(id = R.string.toConsole),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = BlockRegularTextStyle
            )
        }
    }
}