package com.example.codeblocks.domain.entity

class FunctionDeclarator : BlockWithNesting() {

    override fun execute() {
        val function = Function()
        function.nestedBlocks = nestedBlocks
        function.setParams(paramBundle!!)
        // TODO error handling
        function.setScope(scope!!)
        // TODO error handling
        scope!!.addFunction(function)
        // TODO error handling
    }

    override fun setParams(paramBundle: ParamBundle) {
        if (paramBundle is FunctionSignature) {
            this.paramBundle = paramBundle
        } else {
            // TODO error handling
        }
    }

}