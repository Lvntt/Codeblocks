package com.example.codeblocks.domain.entity

class Program : Executable {

    private var scope = Scope()

    var blocks: MutableList<Block> = mutableListOf()

    override fun execute() {
        scope = Scope()
        blocks.forEach {
            it.setupScope(scope)
            it.execute()
        }
    }

}