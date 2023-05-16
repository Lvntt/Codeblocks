package com.example.codeblocks.domain.entity

import kotlin.reflect.KClass

abstract class Block : Executable {

    protected var paramBundle: ParamBundle? = null

    protected var scope: Scope? = null

    protected abstract val paramType: KClass<out ParamBundle>

    override suspend fun execute() {
        if (paramBundle == null) { /*TODO error handling*/ throw Exception() }
        if (paramBundle!!::class != paramType) { /*TODO error handling*/ throw Exception() }
        if (scope == null) { /*TODO error handling*/ throw Exception() }

        executeAfterChecks(scope!!)
    }

    protected abstract suspend fun executeAfterChecks(scope: Scope)

    fun setParams(paramBundle: ParamBundle) {
        if (paramType != paramBundle::class) { /*TODO error handling*/ throw Exception() }

        this.paramBundle = paramBundle
    }

    fun setupScope(scope: Scope) {
        this.scope = scope
    }

}