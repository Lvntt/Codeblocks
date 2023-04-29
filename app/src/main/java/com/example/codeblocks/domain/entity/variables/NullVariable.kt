package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable

class NullVariable(name: String): Variable(name) {
    override fun copy(newName: String): Variable {
        return NullVariable(newName)
    }

    override fun toString(): String = null.toString()
}