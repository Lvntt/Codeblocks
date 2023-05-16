package com.example.codeblocks.presentation.block.data

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

data class BlockWithNestingData(
    override val blockClass: KClass<out Block>,
    override val blockParametersData: BlockParameters,
    val nestedBlocksData: MutableList<BlockData> = mutableListOf()
) : BlockData() {
    override fun createBlock(): Block {
        val block = blockClass.createInstance()
        block.setParams(blockParametersData.createBundle())
        nestedBlocksData.forEach {
            (block as BlockWithNesting).nestedBlocks.add(it.createBlock())
        }
        return block
    }
}
