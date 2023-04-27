package com.example.codeblocks.domain.entity

abstract class Block : Executable {

    protected var paramBundle: ParamBundle? = null

    protected var scope: Scope? = null

    abstract override fun execute()

    abstract fun setParams(paramBundle: ParamBundle)

    fun setScope(scope: Scope) {
        this.scope = scope
    }

}