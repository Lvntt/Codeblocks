package com.example.codeblocks.ui.view.blocks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.codeblocks.R
import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.variables.IntegerVariable
import com.example.codeblocks.presentation.block.parameters.FunctionDeclarationParameters
import com.example.codeblocks.ui.AvailableVariableTypes
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockHeight
import com.example.codeblocks.ui.theme.BlockMinimumWidth
import com.example.codeblocks.ui.theme.BlockPadding
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.SpacerBetweenInnerElementsWidth
import com.example.codeblocks.ui.view.common.VariableNameTextField
import com.example.codeblocks.ui.view.common.VariableTypesDropdownMenu

@Composable
fun FunctionDeclarationBlock(
    modifier: Modifier = Modifier,
    parameters: FunctionDeclarationParameters = FunctionDeclarationParameters(),
    onAddBlockClick: () -> Unit = {},
    isEditable: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val paramsSignature = parameters.paramsSignature

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
                text = stringResource(id = R.string.function),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                placeholderId = R.string.namePlaceholder,
                isEditable = isEditable
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = R.string.openingBracket),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            for (parameterIndex in paramsSignature.indices) {
                val visibilityState =
                    remember { MutableTransitionState(parameters.paramsVisibilityState[parameterIndex]) }.apply {
                        targetState = true
                    }
                LaunchedEffect(visibilityState.currentState && !parameters.paramsVisibilityState[parameterIndex]) {
                    if (visibilityState.currentState && !parameters.paramsVisibilityState[parameterIndex]) {
                        parameters.paramsVisibilityState[parameterIndex] = true
                    }
                }
                AnimatedVisibility(visibleState = visibilityState) {
                    Row(modifier = modifier.fillMaxHeight()) {
                        VariableNameTextField(
                            parameterName = paramsSignature[parameterIndex].first,
                            onValueChange = {
                                paramsSignature[parameterIndex] =
                                    Pair(it, paramsSignature[parameterIndex].second)
                            },
                            placeholderId = R.string.namePlaceholder,
                            isEditable = isEditable
                        )

                        Spacer(
                            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                        )

                        Box(
                            modifier = modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.colon),
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = BlockRegularTextStyle
                            )
                        }

                        Spacer(
                            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                        )

                        VariableTypesDropdownMenu(
                            getCurrentType = { paramsSignature[parameterIndex].second },
                            setCurrentType = {
                                paramsSignature[parameterIndex] =
                                    Pair(paramsSignature[parameterIndex].first, it)
                            },
                            variableTypesMap = AvailableVariableTypes.typenameToKClass
                        )

                        Spacer(
                            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                        )

                        Box(
                            modifier = modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.comma),
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = BlockRegularTextStyle
                            )
                        }

                        Spacer(
                            modifier = modifier.width(SpacerBetweenInnerElementsWidth)
                        )
                    }
                }
            }

            AddExpressionBlock {
                if (isEditable) {
                    paramsSignature.add(Pair(DefaultValues.EMPTY_STRING, IntegerVariable::class))
                    parameters.paramsVisibilityState.add(false)
                }
            }

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = R.string.closingBracket),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            Text(
                text = stringResource(id = R.string.colon),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = BlockRegularTextStyle
            )

            Spacer(
                modifier = modifier.width(SpacerBetweenInnerElementsWidth)
            )

            VariableTypesDropdownMenu(
                getCurrentType = { parameters.returnType },
                setCurrentType = { parameters.returnType = it },
                variableTypesMap = AvailableVariableTypes.functionTypenameToKClass
            )
        }
    }
}