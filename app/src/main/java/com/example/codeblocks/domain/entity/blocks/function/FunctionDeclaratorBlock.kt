package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.parambundles.function.FunctionSignature
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Scope
import kotlin.reflect.KClass

class FunctionDeclaratorBlock : BlockWithNesting() {
    override val paramType: KClass<out ParamBundle> = FunctionSignature::class

    override fun executeAfterChecks(scope: Scope) {
        val function = FunctionBlock()
        function.nestedBlocks = nestedBlocks
        function.setParams(paramBundle!!)
        function.setupScope(scope)
        scope.addFunction(function)
    }

}