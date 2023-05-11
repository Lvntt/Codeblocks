package com.example.codeblocks.presentation.block

import com.example.codeblocks.domain.entity.Block
import kotlin.reflect.KClass

data class SimpleBlockData(
    override val blockClass: KClass<out Block>,
    override val blockParametersData: BlockParameters
) : BlockData()