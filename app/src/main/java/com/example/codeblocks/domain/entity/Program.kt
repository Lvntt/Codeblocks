package com.example.codeblocks.domain.entity

class Program : Executable {

    private val scope = Scope()

    var blocks: MutableList<Block> = mutableListOf()

    override fun execute() {
        blocks.forEach {
            it.setScope(scope)
            it.execute()
        }
    }

}