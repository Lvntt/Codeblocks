package com.example.codeblocks.presentation.block

import com.example.codeblocks.domain.entity.Block
import kotlin.reflect.KClass

abstract class BlockData {
    abstract val blockClass: KClass<out Block>
    abstract val blockParametersData: BlockParameters
}
