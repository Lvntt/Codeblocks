package com.example.codeblocks.presentation.block

import com.example.codeblocks.domain.entity.Block
import kotlin.reflect.KClass

data class BlockWithNestingData(
    override val blockClass: KClass<out Block>,
    override val blockParametersData: BlockParameters,
    val nestedBlocksData: MutableList<BlockData> = mutableListOf()
) : BlockData()
