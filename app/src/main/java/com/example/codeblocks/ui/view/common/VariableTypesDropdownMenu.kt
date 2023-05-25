package com.example.codeblocks.ui.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.ui.theme.BlockAccentedTextStyle
import com.example.codeblocks.ui.theme.BlockElementShape
import com.example.codeblocks.ui.theme.BlockRegularTextStyle
import com.example.codeblocks.ui.theme.VariableTypeChoiceHeight
import com.example.codeblocks.ui.theme.VariableTypeChoiceWidth
import kotlin.reflect.KClass

@Composable
fun VariableTypesDropdownMenu(
    getCurrentType: () -> KClass<out Variable>,
    setCurrentType: (KClass<out Variable>) -> Unit,
    variableTypesMap: Map<Int, KClass<out Variable>>,
    modifier: Modifier = Modifier,
    isEditable: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val chosenTypeStringRes =
        variableTypesMap.filterValues { it == getCurrentType() }.keys.toList()[0]
    val availableVariableTypes = variableTypesMap.keys.toList()
    var dropdownMenuExpanded by remember { mutableStateOf(false) }
    var dropdownMenuSelectedItem by remember { mutableStateOf(chosenTypeStringRes) }

    var customModifier = modifier
    if (isEditable) {
        customModifier = customModifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            dropdownMenuExpanded = true
        }
    }

    Box(
        modifier = modifier
            .height(VariableTypeChoiceHeight)
            .width(VariableTypeChoiceWidth)
            .clip(BlockElementShape)
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = dropdownMenuSelectedItem),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = customModifier,
            style = BlockAccentedTextStyle
        )
        DropdownMenu(
            expanded = dropdownMenuExpanded,
            onDismissRequest = {
                dropdownMenuExpanded = false
            }
        ) {
            availableVariableTypes.forEach { type ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = type),
                            style = BlockRegularTextStyle
                        )
                    },
                    onClick = {
                        val blockKClass = variableTypesMap[type]
                        if (blockKClass != null) {
                            setCurrentType(blockKClass)
                            dropdownMenuSelectedItem = type
                            dropdownMenuExpanded = false
                        }
                    }
                )
            }
        }
    }
}