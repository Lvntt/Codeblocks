package com.example.codeblocks.domain.entity

class Function : BlockWithNesting() {

    private var paramValues: ParamBundle? = null
    private var returnedVariable: Variable? = null

    override fun execute() {
        val nestedScope = NestedScope(scope!!)
        // TODO error handling
        if (paramValues is FunctionParamValues) {
            (paramValues as FunctionParamValues).variableList.forEachIndexed { index, variable ->
                nestedScope.addVariable(
                    variable.copy(
                        (paramBundle as FunctionSignature).paramsSignature[index].first
                    )
                )
            }
        }

        nestedBlocks.forEach {
            it.setScope(nestedScope)
            it.execute()

            if (it is ReturnBlock) {
                // TODO write return block and catch returned value
                return
            }
        }
    }

    override fun setParams(paramBundle: ParamBundle) {
        if (paramBundle is FunctionSignature) {
            this.paramBundle = paramBundle
        } else {
            // TODO error handling
        }
    }

    fun setValues(paramBundle: ParamBundle) {
        if (this.paramBundle is FunctionSignature) {
            if (paramBundle is FunctionParamValues) {
                paramBundle.variableList.forEachIndexed { index, variable ->
                    if (typeMismatches(index, variable)) {
                        // TODO error handling
                    }
                }
                paramValues = paramBundle
            }
        }
    }

    private fun typeMismatches(
        index: Int,
        variable: Variable
    ) = (index < (this.paramBundle as FunctionSignature).paramsSignature.size
            && variable::class != (this.paramBundle as FunctionSignature).paramsSignature[index].second::class)

    fun getName(): String = TODO()

}