package com.example.codeblocks.domain.entity.blocks.variable

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.variable.SetVariableBundle
import com.example.codeblocks.domain.entity.variables.NullVariable
import kotlin.reflect.KClass

class SetVariableBlock: Block() {
    override val paramType: KClass<out ParamBundle> = SetVariableBundle::class

    override fun executeAfterChecks(scope: Scope) {
        (paramBundle as SetVariableBundle).expression.setupScope(scope)
        val returnResult = (paramBundle as SetVariableBundle).expression.getReturnedValue()
        if(returnResult != null) {
            val originalVariable = scope.findVariable((paramBundle as SetVariableBundle).name)
            if(originalVariable != null && originalVariable::class == returnResult::class) {
                scope.setVariable(originalVariable.name, returnResult)
            } else if(originalVariable != null && returnResult is NullVariable) {
                val setValueCallable = originalVariable::class.members.single { it.name == "setValue" }
                val originalNullCopy = originalVariable.copy(originalVariable.name)
                setValueCallable.call(originalNullCopy, null)
                scope.setVariable(originalVariable.name, originalNullCopy)
            } else {
                //TODO error handling
            }
        } else {
            //TODO error handling
        }
    }
}