package com.example.codeblocks.domain.entity

import com.example.codeblocks.domain.entity.blocks.function.FunctionBlock

open class Scope {

    protected val variables: MutableMap<String,Variable> = mutableMapOf()

    private val functions: MutableMap<String,FunctionBlock> = mutableMapOf()

    fun addVariable(variable: Variable) {
        if(variables[variable.name] != null) /*TODO error handling*/ throw Exception()
        variables[variable.name] = variable
    }

    fun addFunction(function: FunctionBlock) {
        if(functions[function.getName()] != null) /*TODO error handling*/ throw Exception()
        functions[function.getName()] = function
    }

    open fun findVariable(name: String): Variable? {
        return variables[name]
    }

    open fun findFunction(name: String): FunctionBlock? {
        return functions[name]
    }

    open fun setVariable(name: String, variable: Variable) {
        if(variables[name] == null) /*TODO error handling*/ throw Exception()
        variables[name] = variable.copy(name)
    }

}