package com.example.codeblocks.domain.entity

open class Scope {

    private val variables: MutableList<Variable> = mutableListOf()

    private val functions: MutableList<Function> = mutableListOf()

    open fun addVariable(variable: Variable) {
        variables.add(variable)
    }

    open fun addFunction(function: Function) {
        functions.add(function)
    }

    open fun findVariable(name: String): Variable? {
        return variables.find { it.getName() == name }
    }

    open fun findFunction(name: String): Function? {
        return functions.find { it.getName() == name }
    }

}