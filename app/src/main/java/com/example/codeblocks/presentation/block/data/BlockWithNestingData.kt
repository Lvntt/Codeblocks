package com.example.codeblocks.presentation.block.data

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import kotlin.reflect.KClass

data class BlockWithNestingData(
    override val blockClass: KClass<out Block>,
    override val blockParametersData: BlockParameters,
    val nestedBlocksData: MutableList<BlockData> = mutableListOf()
) : BlockData()
