package com.example.codeblocks.domain.entity

abstract class BlockWithNesting : Block() {

    var nestedBlocks: MutableList<Block> = mutableListOf()
    //Upon finding a stop block, blockwithnesting should set this variable to found block and stop execution
    var stopCallingBlock: StopExecutionBlock? = null

}