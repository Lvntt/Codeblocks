package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.blocks.expression.ExpressionBlock
import com.example.codeblocks.domain.entity.parambundles.function.FunctionParamValues
import com.example.codeblocks.domain.entity.parambundles.function.LoadedFunctionParams
import kotlin.reflect.KClass

class FunctionCallBlock : ExpressionBlock() {
    override val paramType: KClass<out ParamBundle> = FunctionParamValues::class

    override fun executeAfterChecks(scope: Scope) {
        val functionParams = LoadedFunctionParams()
        (paramBundle as FunctionParamValues).expressionList.forEach {
            val expressionResult = getVariableFromParams(it, scope)
            if(expressionResult != null) {
                functionParams.addParam(expressionResult)
            } else {
                //TODO error handling
            }
        }
        val function = scope.findFunction((paramBundle as FunctionParamValues).functionName)
        if(function != null) {
            function.setValues(functionParams)
            returnedVariable = function.getReturnedValue()
        } else {
            //TODO error handling
        }
    }
}