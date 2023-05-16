package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.function.FunctionParamValues
import com.example.codeblocks.domain.entity.parambundles.function.LoadedFunctionParams
import kotlin.reflect.KClass

class FunctionCallBlock : ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = FunctionParamValues::class

    override suspend fun executeAfterChecks(scope: Scope) {
        val functionParams = LoadedFunctionParams()
        (paramBundle as FunctionParamValues).expressionList.forEach {
            val expressionResult = getVariableFromParams(it, scope) ?: /*TODO error handling*/ throw Exception()
            functionParams.addParam(expressionResult)
        }

        val function = scope.findFunction((paramBundle as FunctionParamValues).functionName) ?: /*TODO error handling*/ throw Exception()
        function.setValues(functionParams)
        returnedVariable = function.getReturnedValue()
    }

}