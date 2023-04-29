package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable

class BooleanVariable(name: String): Variable(name) {
    private var value: Boolean? = null

    fun getValue(): Boolean? = null

    fun setValue(value: Boolean?) {
        this.value = value
    }

    override fun copy(newName: String): Variable {
        val newVariable = BooleanVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

    override fun toString(): String = value.toString()

}