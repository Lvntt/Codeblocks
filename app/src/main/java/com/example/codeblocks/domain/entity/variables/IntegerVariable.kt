package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable

class IntegerVariable(name: String): Variable(name) {
    private var value: Int? = null

    fun getValue(): Int? = value

    fun setValue(value: Int?) {
        this.value = value
    }

    override fun copy(newName: String): Variable {
        val newVariable = IntegerVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }
}