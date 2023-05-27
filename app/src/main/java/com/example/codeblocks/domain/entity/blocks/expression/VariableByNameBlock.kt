package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.expression.VariableBundle
import kotlin.reflect.KClass

class VariableByNameBlock : ExpressionBlock() {

    override val paramType: KClass<out ParamBundle> = VariableBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        returnedVariable = scope.findVariable((paramBundle as VariableBundle).value)
    }

}