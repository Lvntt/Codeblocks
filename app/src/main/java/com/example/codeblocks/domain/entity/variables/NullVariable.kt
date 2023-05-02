package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable

class NullVariable(name: String) : Variable(name) {

    fun getValue(): Any? = null
    override fun copy(newName: String): Variable {
        return NullVariable(newName)
    }
}