package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.VariableNameBundle
import kotlin.reflect.KClass

class VariableByNameBlock : ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = VariableNameBundle::class

    override fun executeAfterChecks(scope: Scope) {
        returnedVariable = scope.findVariable((paramBundle as VariableNameBundle).variableName)
    }

}