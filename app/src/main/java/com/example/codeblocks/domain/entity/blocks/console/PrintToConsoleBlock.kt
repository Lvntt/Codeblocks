package com.example.codeblocks.domain.entity.blocks.console

import android.util.Log
import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.SingleExpressionBlockBundle
import kotlin.reflect.KClass

class PrintToConsoleBlock: Block() {
    override val paramType: KClass<out ParamBundle> = SingleExpressionBlockBundle::class

    override fun executeAfterChecks(scope: Scope) {
        (paramBundle as SingleExpressionBlockBundle).expressionBlock.setupScope(scope)
        val returnResult = (paramBundle as SingleExpressionBlockBundle).expressionBlock.getReturnedValue() ?: /*TODO error handling*/ throw Exception()
        val resultValue = returnResult::class.members.single { it.name == "getValue" }.call(returnResult)

        //TODO implement in-app console
        Log.d("INTERPRETER", resultValue.toString())
    }
}