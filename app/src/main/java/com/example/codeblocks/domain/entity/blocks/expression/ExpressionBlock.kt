package com.example.codeblocks.domain.entity.blocks.expression

import com.example.codeblocks.domain.entity.Block
import com.example.codeblocks.domain.entity.Returnable
import com.example.codeblocks.domain.entity.Variable

abstract class ExpressionBlock: Block(), Returnable {
    protected var returnedVariable: Variable? = null

    override fun getReturnedValue(): Variable? {
        execute()
        return returnedVariable
    }

    protected fun getVariableFromParams(returnable: Returnable): Variable? {
        if(returnable is Block) {
            returnable.setupScope(scope!!)
            return returnable.getReturnedValue()
        }
        if(returnable is Variable) {
            return returnable
        }
        return null
    }
}