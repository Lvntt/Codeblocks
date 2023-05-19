package com.example.codeblocks.domain.entity.blocks.loop

import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.NestedScope
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.parambundles.expression.ForExpressionBlockBundle
import com.example.codeblocks.domain.entity.variables.BooleanVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts
import com.example.codeblocks.domain.entity.variables.VariableCasts.typeCanBeSeamlesslyConverted
import kotlin.reflect.KClass

class ForBlock : BlockWithNesting() {

    override val paramType: KClass<out ParamBundle> = ForExpressionBlockBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        stopCallingBlock = null
        val expressionNestedScope = NestedScope(scope)
        (paramBundle as ForExpressionBlockBundle).initBlock.setupScope(expressionNestedScope)
        (paramBundle as ForExpressionBlockBundle).initBlock.execute()

        while (true) {
            val nestedScope = NestedScope(expressionNestedScope)
            (paramBundle as ForExpressionBlockBundle).expressionBlock.setupScope(expressionNestedScope)
            var returnedValue = (paramBundle as ForExpressionBlockBundle).expressionBlock.getReturnedValue()
                ?: /*TODO error handling*/ throw Exception()

            if (!typeCanBeSeamlesslyConverted(returnedValue, BooleanVariable::class)) { /*TODO error handling*/ throw Exception() }
            returnedValue = VariableCasts.castVariable(returnedValue, BooleanVariable::class) ?: /*TODO error handling*/ throw Exception()
            val booleanValue = (returnedValue as BooleanVariable).getValue() ?: /*TODO error handling*/ throw Exception()

            if (!booleanValue) { return }
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

            (paramBundle as ForExpressionBlockBundle).afterIterationBlock.setupScope(expressionNestedScope)
            (paramBundle as ForExpressionBlockBundle).afterIterationBlock.execute()
        }
    }

}