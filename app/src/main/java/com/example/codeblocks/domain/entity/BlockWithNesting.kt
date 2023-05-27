package com.example.codeblocks.domain.entity

abstract class BlockWithNesting : Block() {

    var nestedBlocks: MutableList<Block> = mutableListOf()

    var stopCallingBlock: StopExecutionBlock? = null

}