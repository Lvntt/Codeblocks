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
import com.example.codeblocks.presentation.block.parameters.FunctionCallParameters
import com.example.codeblocks.ui.navigation.CodeblocksDestinations
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.SpacerBetweenInnerElementsWidth
import com.example.codeblocks.ui.view.common.ComposableByExpressionBlockClass
import com.example.codeblocks.ui.view.common.VariableNameTextField
import kotlin.reflect.KClass

@Composable
fun FunctionCallBlock(
    navController: NavController,
    modifier: Modifier = Modifier,
    setAddBlockCallback: ((KClass<out Block>) -> Unit) -> Unit = {},
    createBlockDataByType: (KClass<out Block>) -> BlockData? = { null },
    parameters: FunctionCallParameters = FunctionCallParameters(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val onAddParameterValueClick = {
        setAddBlockCallback {
            parameters.passedParameters.add(createBlockDataByType(it) as ExpressionBlockData)
        }
        navController.navigate(CodeblocksDestinations.EXPRESSION_ADDITION_ROUTE)
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
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.call),
            style = BlockRegularTextStyle
        )

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        VariableNameTextField(
            parameterName = parameters.name,
            onValueChange = {
                parameters.name = it
            },
            placeholderId = R.string.function,
            isEditable = isEditable
        )

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        Text(
            text = stringResource(id = R.string.openingBracket),
            style = BlockRegularTextStyle
        )

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        for (parameter in parameters.passedParameters) {
            ComposableByExpressionBlockClass(
                navController = navController,
                parametersExpression = parameter,
                setAddBlockCallback = setAddBlockCallback,
                createBlockDataByType = createBlockDataByType
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = R.string.comma),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )
        }

        AddExpressionBlock(
            isEditable = isEditable,
            onClick = { onAddParameterValueClick() }
        )

        Spacer(
            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
        )

        Text(
            text = stringResource(id = R.string.closingBracket),
            style = BlockRegularTextStyle
        )
    }
}