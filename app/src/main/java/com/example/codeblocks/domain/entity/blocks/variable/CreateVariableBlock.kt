package com.example.codeblocks.domain.entity.blocks.variable

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.variable.CreateVariableBundle
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class CreateVariableBlock : Block() {

    override val paramType: KClass<out ParamBundle> = CreateVariableBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        val variable = (paramBundle as CreateVariableBundle).type.primaryConstructor?.call((paramBundle as CreateVariableBundle).name) ?: /*TODO error handling*/ throw Exception()
        scope.addVariable(variable)
    }

}