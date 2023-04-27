package com.example.codeblocks.domain.entity

class NestedScope(private val parentScope: Scope) : Scope() {

    override fun findVariable(name: String): Variable? {
        return super.findVariable(name) ?: parentScope.findVariable(name)
    }

    override fun findFunction(name: String): Function? {
        return super.findFunction(name) ?: parentScope.findFunction(name)
    }

}