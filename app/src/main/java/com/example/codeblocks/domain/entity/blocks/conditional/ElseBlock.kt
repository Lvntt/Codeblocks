package com.example.codeblocks.domain.entity.blocks.conditional

import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.NestedScope
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.parambundles.EmptyParamBundle
import kotlin.reflect.KClass

class ElseBlock : BlockWithNesting() {

    override val paramType: KClass<out ParamBundle> = EmptyParamBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        stopCallingBlock = null
        val nestedScope = NestedScope(scope)
        for (block in nestedBlocks) {
            block.setupScope(nestedScope)
            block.execute()
            if (block is BlockWithNesting && block.stopCallingBlock != null) {
                stopCallingBlock = block.stopCallingBlock
                block.stopCallingBlock = null
                return
            } else if (block is StopExecutionBlock) {
                stopCallingBlock = block
                return
            }
        }
    }

}