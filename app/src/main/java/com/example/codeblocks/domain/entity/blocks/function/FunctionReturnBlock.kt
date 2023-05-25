package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.Variable
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import com.example.codeblocks.domain.entity.parambundles.function.FunctionReturnBundle
import com.example.codeblocks.domain.entity.variables.NullVariable
import kotlin.reflect.KClass

class FunctionReturnBlock : StopExecutionBlock() {

    override val paramType: KClass<out ParamBundle> = FunctionReturnBundle::class
    var returnResult: Variable? = null

    override suspend fun executeAfterChecks(scope: Scope) {
        (paramBundle as FunctionReturnBundle).expressionBlock?.setupScope(scope)
        returnResult = (paramBundle as FunctionReturnBundle).expressionBlock?.getReturnedValue()

        if (returnResult == null) {
            returnResult = NullVariable(DefaultValues.EMPTY_STRING)
        }
    }

}