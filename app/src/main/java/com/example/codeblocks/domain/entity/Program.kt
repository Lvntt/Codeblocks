package com.example.codeblocks.domain.entity

class Program : Executable {

    private var scope = Scope()

    var blocks: MutableList<Block> = mutableListOf()

    override suspend fun execute() {
        scope = Scope()
        blocks.forEach {
            it.setupScope(scope)
            it.execute()
            if (it is StopExecutionBlock || it is BlockWithNesting && it.stopCallingBlock is StopExecutionBlock) {
                /*TODO error handling*/ throw Exception()
            }
        }
    }

}