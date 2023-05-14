package com.example.codeblocks.domain.entity.blocks.variable

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.variable.SetVariableBundle
import com.example.codeblocks.domain.entity.variables.NullVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts.castVariable
import com.example.codeblocks.domain.entity.variables.VariableCasts.typeCanBeSeamlesslyConverted
import kotlin.reflect.KClass

class SetVariableBlock : Block() {

    override val paramType: KClass<out ParamBundle> = SetVariableBundle::class

    override fun executeAfterChecks(scope: Scope) {
        (paramBundle as SetVariableBundle).expression.setupScope(scope)
        val returnResult = (paramBundle as SetVariableBundle).expression.getReturnedValue() ?: /*TODO error handling*/ throw Exception()

        val originalVariable = scope.findVariable((paramBundle as SetVariableBundle).name)
        if (originalVariable != null && typeCanBeSeamlesslyConverted(returnResult, originalVariable::class)) {
            scope.setVariable(originalVariable.name, castVariable(returnResult,originalVariable::class)!!)
        } else {
            //TODO error handling
        }
    }

}