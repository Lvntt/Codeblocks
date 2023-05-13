package com.example.codeblocks.presentation.block.data

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import kotlin.reflect.KClass

data class SimpleBlockData(
    override val blockClass: KClass<out Block>,
    override val blockParametersData: BlockParameters
) : BlockData()