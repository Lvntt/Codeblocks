package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.parambundles.function.FunctionReturnBundle
import kotlin.reflect.KClass

class FunctionReturnBlock: StopExecutionBlock() {
    override val paramType: KClass<out ParamBundle> = FunctionReturnBundle::class

    override fun executeAfterChecks(scope: Scope) {
        (paramBundle as FunctionReturnBundle).expression.setupScope(scope)
        val returnResult = (paramBundle as FunctionReturnBundle).expression.getReturnedValue()
        if(returnResult != null) {
            val function = scope.findFunction((paramBundle as FunctionReturnBundle).functionName)
            if(function != null) {
                function.callReturn(returnResult)
            } else {
                //TODO error handling
            }
        } else {
            //TODO error handling
        }
    }
}