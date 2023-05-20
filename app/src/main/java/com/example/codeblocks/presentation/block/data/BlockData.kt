package com.example.codeblocks.presentation.block.data

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.presentation.block.parameters.BlockParameters
import java.util.UUID
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class BlockData {
    var parentBlockList: MutableList<BlockData>? = null
    var parentBlockListIndex: Int = -1
    val id: UUID = UUID.randomUUID()
    abstract val blockClass: KClass<out Block>
    abstract val blockParametersData: BlockParameters

    open fun createBlock(): Block {
        val block = blockClass.createInstance()
        block.setParams(blockParametersData.createBundle())
        return block
    }
}
