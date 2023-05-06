package com.example.codeblocks.domain.entity

abstract class BlockWithNesting : Block() {

    var nestedBlocks: MutableList<Block> = mutableListOf()

    //Upon finding a stop block, block with nesting should set this variable to found block and stop execution
    //If a block with nesting finds an another block with nesting that has a non-null stopCallingBlock, it
    //should reset that block to null, set its own stopCallingBlock to found block and stop execution
    //and so on
    var stopCallingBlock: StopExecutionBlock? = null

}