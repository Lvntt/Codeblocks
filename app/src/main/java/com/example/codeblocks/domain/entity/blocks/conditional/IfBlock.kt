package com.example.codeblocks.domain.entity.blocks.conditional

import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.NestedScope
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.BooleanVariable
import kotlin.reflect.KClass

class IfBlock: BlockWithNesting() {
    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class

    override fun executeAfterChecks(scope: Scope) {
        stopCallingBlock = null
        (paramBundle as SingleExpressionBlockBundle).expressionBlock.setupScope(scope)
        val returnedValue = (paramBundle as SingleExpressionBlockBundle).expressionBlock.getReturnedValue()
        if(returnedValue is BooleanVariable) {
            val value = returnedValue.getValue()
            if(value != null) {
                if(value) {
                    val nestedScope = NestedScope(scope)
                    for(block in nestedBlocks) {
                        block.setupScope(nestedScope)
                        block.execute()
                        if(block is StopExecutionBlock || block is BlockWithNesting && block.stopCallingBlock != null) {
                            if(block is BlockWithNesting) {
                                stopCallingBlock = block.stopCallingBlock
                                block.stopCallingBlock = null
                            } else if(block is StopExecutionBlock) {
                                stopCallingBlock = block
                            }
                            return
                        }
                    }
                }
            } else {
                //TODO error handling
            }
        } else {
            //TODO error handling
        }
    }
}