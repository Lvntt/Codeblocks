package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import kotlin.reflect.KClass

class FunctionReturnBlock : StopExecutionBlock() {

    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class
    var returnResult: Variable? = null

    override suspend fun executeAfterChecks(scope: Scope) {
        (paramBundle as SingleExpressionBlockBundle).expressionBlock.setupScope(scope)
        returnResult = (paramBundle as SingleExpressionBlockBundle).expressionBlock.getReturnedValue() ?: /*TODO error handling*/ throw Exception()
    }

}