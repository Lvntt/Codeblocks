package com.example.codeblocks.domain.entity

import com.example.codeblocks.domain.entity.blocks.function.FunctionBlock

open class Scope {

    protected val variables: MutableList<Variable> = mutableListOf()

    private val functions: MutableList<FunctionBlock> = mutableListOf()

    fun addVariable(variable: Variable) {
        variables.add(variable)
    }

    fun addFunction(function: FunctionBlock) {
        functions.add(function)
    }

    open fun findVariable(name: String): Variable? {
        return variables.find { it.name == name }
    }

    open fun findFunction(name: String): FunctionBlock? {
        return functions.find { it.getName() == name }
    }

    open fun setVariable(name: String, variable: Variable) {
        val index = variables.indexOfFirst { it.name == name }
        if (index == -1) { /*TODO error handling*/ throw Exception() }

        variables[index] = variable.copy(name)
    }

}