package com.example.codeblocks.presentation.block.data

import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import kotlin.reflect.KClass

data class ExpressionBlockData(
    override val blockClass: KClass<out ExpressionBlock>,
    override val blockParametersData: BlockParameters
) : BlockData()