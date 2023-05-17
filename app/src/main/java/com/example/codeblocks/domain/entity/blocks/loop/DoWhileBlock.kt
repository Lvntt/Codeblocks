package com.example.codeblocks.domain.entity.blocks.loop

import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.NestedScope
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.BooleanVariable
import kotlin.reflect.KClass

class DoWhileBlock  : BlockWithNesting() {

    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class

    override fun executeAfterChecks(scope: Scope) {
        stopCallingBlock = null

        while (true) {
            val nestedScope = NestedScope(scope)
            for (block in nestedBlocks) {
                block.setupScope(nestedScope)
                block.execute()
                if (block is BlockWithNesting && block.stopCallingBlock != null) {
                    if (block.stopCallingBlock is BreakBlock) { return }
                    if (block.stopCallingBlock is ContinueBlock) { break }
                    stopCallingBlock = block.stopCallingBlock
                    block.stopCallingBlock = null
                    return
                } else if (block is StopExecutionBlock) {
                    if (stopCallingBlock is BreakBlock) { return }
                    if (stopCallingBlock is ContinueBlock) { break }
                    stopCallingBlock = block
                    return
                }
            }

            (paramBundle as SingleExpressionBlockBundle).expressionBlock.setupScope(scope)
            val returnedValue =
                (paramBundle as SingleExpressionBlockBundle).expressionBlock.getReturnedValue()

            if (returnedValue !is BooleanVariable) { /*TODO error handling*/ throw Exception()
            }
            val booleanValue = returnedValue.getValue() ?: /*TODO error handling*/ throw Exception()

            if (!booleanValue) { return }
        }
    }
}