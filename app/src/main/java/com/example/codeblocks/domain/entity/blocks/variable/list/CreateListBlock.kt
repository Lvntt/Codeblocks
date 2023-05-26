package com.example.codeblocks.domain.entity.blocks.variable.list

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.parambundles.variable.CreateVariableBundle
import com.example.codeblocks.domain.entity.variables.ListVariable
import kotlin.reflect.KClass

class CreateListBlock : Block() {

    override val paramType: KClass<out ParamBundle> = CreateVariableBundle::class

    override suspend fun executeAfterChecks(scope: Scope) {
        val listElementType = (paramBundle as CreateVariableBundle).type
        val listName = (paramBundle as CreateVariableBundle).name
        val list = ListVariable(listName, listElementType)
        scope.addVariable(list)
    }

}