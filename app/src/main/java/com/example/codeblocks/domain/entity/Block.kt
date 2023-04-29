package com.example.codeblocks.domain.entity

import kotlin.reflect.KClass

abstract class Block : Executable {

    protected var paramBundle: ParamBundle? = null

    protected var scope: Scope? = null

    protected abstract val paramType: KClass<out ParamBundle>

    override fun execute() {
        if(paramBundle == null) {
            //TODO error handling
        }
        else if(paramBundle!!::class != paramType) {
            //TODO error handling
        }
        else if(scope == null) {
            //TODO error handling
        } else {
            executeAfterChecks()
        }
    }

    protected abstract fun executeAfterChecks()
    fun setParams(paramBundle: ParamBundle) {
        if(paramType == paramBundle::class) {
            this.paramBundle = paramBundle
        } else {
            //TODO Error handling
        }
    }

    fun setupScope(scope: Scope) {
        this.scope = scope
    }

}