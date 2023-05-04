package com.example.codeblocks.domain.entity.blocks.function

import com.example.codeblocks.domain.entity.BlockWithNesting
import com.example.codeblocks.domain.entity.parambundles.function.LoadedFunctionParams
import com.example.codeblocks.domain.entity.parambundles.function.FunctionSignature
import com.example.codeblocks.domain.entity.NestedScope
import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Returnable
import com.example.codeblocks.domain.entity.Scope
import com.example.codeblocks.domain.entity.StopExecutionBlock
import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class FunctionBlock : BlockWithNesting(), Returnable {

    private var paramValues: ParamBundle? = null
    private var returnedVariable: Variable? = null
    override val paramType: KClass<out ParamBundle> = FunctionSignature::class

    override fun executeAfterChecks(scope: Scope) {
        stopCallingBlock = null
        returnedVariable = null

        val nestedScope = NestedScope(scope)
        if (paramValues !is LoadedFunctionParams) { /*TODO error handling*/ throw Exception()
        }
        (paramValues as LoadedFunctionParams).variableList.forEachIndexed { index, variable ->
            nestedScope.addVariable(
                variable.copy(
                    (paramBundle as FunctionSignature).paramsSignature[index].first
                )
            )
        }

        for (block in nestedBlocks) {
            block.setupScope(nestedScope)
            block.execute()
            if (!(block is StopExecutionBlock || block is BlockWithNesting && block.stopCallingBlock is StopExecutionBlock)) continue
            if (!(block is FunctionReturnBlock || block is BlockWithNesting && block.stopCallingBlock is FunctionReturnBlock)) { /*TODO error handling*/ throw Exception() }
            if (block is BlockWithNesting) block.stopCallingBlock = null
            return
        }
        returnedVariable =
            (paramBundle as FunctionSignature).returnType.primaryConstructor?.call("")
    }

    fun setValues(paramBundle: ParamBundle) {
        if (this.paramBundle !is FunctionSignature) { /*TODO error handling*/ throw Exception()
        }
        if (paramBundle !is LoadedFunctionParams) { /*TODO error handling*/ throw Exception()
        }
        if (paramBundle.variableList.size != (this.paramBundle as FunctionSignature).paramsSignature.size) { /*TODO error handling*/ throw Exception()
        }
        paramBundle.variableList.forEachIndexed { index, variable ->
            if (typeMismatches(index, variable)) { /*TODO error handling*/ throw Exception()
            }
        }

        paramValues = paramBundle
    }

    private fun typeMismatches(
        index: Int,
        variable: Variable
    ) = (index >= (this.paramBundle as FunctionSignature).paramsSignature.size
            || variable::class != (this.paramBundle as FunctionSignature).paramsSignature[index].second)

    fun getName(): String = (paramBundle as FunctionSignature).name

    fun callReturn(returnedVariable: Variable) {
        if (paramBundle !is FunctionSignature) { /*TODO error handling*/ throw Exception()
        }
        if (returnedVariable::class != (paramBundle as FunctionSignature).returnType) { /*TODO error handling*/ throw Exception()
        }

        this.returnedVariable = returnedVariable
    }

    override fun getReturnedValue(): Variable? {
        execute()
        return returnedVariable
    }

}