package com.example.codeblocks.presentation.block.data

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import kotlin.reflect.KClass

abstract class BlockData {
    abstract val blockClass: KClass<out Block>
    abstract val blockParametersData: BlockParameters
}
