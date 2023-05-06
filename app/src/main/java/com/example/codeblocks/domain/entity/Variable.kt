package com.example.codeblocks.domain.entity

import kotlin.reflect.KClass

abstract class Variable(val name: String) : Returnable {

    abstract val valueType: KClass<out Any>

    abstract fun copy(newName: String): Variable

    abstract fun getValue() : Any?

    protected abstract fun setValueAfterChecks(value: Any?)

    fun setValue(value: Any?) {
        if (value != null && value::class != valueType) { /*TODO error handling*/ throw Exception() }
        setValueAfterChecks(value)
    }

    override fun getReturnedValue(): Variable? = this

}