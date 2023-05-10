package com.example.codeblocks.ui

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.blocks.variable.CreateVariableBlock
import kotlin.reflect.KClass

object AvailableBlocks {

    val availableBlocks: List<KClass<out Block>> = listOf(
        CreateVariableBlock::class
    )

}