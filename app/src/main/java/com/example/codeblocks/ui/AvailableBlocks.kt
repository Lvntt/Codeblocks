package com.example.codeblocks.ui

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByNameBlock
import com.example.codeblocks.domain.entity.blocks.expression.VariableByValueBlock
import com.example.codeblocks.domain.entity.blocks.expression.operators.math.PlusBlock
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import com.example.codeblocks.domain.entity.blocks.variable.SetVariableBlock
import kotlin.reflect.KClass

object AvailableBlocks {

    val availableBlocks: List<KClass<out Block>> = listOf(
        CreateVariableBlock::class,
        SetVariableBlock::class
    )

    val availableExpressions: List<KClass<out ExpressionBlock>> = listOf(
        VariableByNameBlock::class,
        VariableByValueBlock::class,
        PlusBlock::class
    )

}