package com.example.codeblocks.domain.entity

import com.example.codeblocks.domain.entity.blocks.function.FunctionBlock

class NestedScope(private val parentScope: Scope) : Scope() {

    override fun findVariable(name: String): Variable? {
        return super.findVariable(name) ?: parentScope.findVariable(name)
    }

    override fun findFunction(name: String): FunctionBlock? {
        return super.findFunction(name) ?: parentScope.findFunction(name)
    }

    override fun setVariable(name: String, variable: Variable) {
        if(variables[name] == null) {
            parentScope.setVariable(name, variable)
        } else {
            variables[name] = variable.copy(name)
        }
    }

}