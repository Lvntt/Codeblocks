package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class NullVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Any::class

    override fun getValue(): Any? = null

    override fun setValueAfterChecks(value: Any?) {
        /*TODO error handling*/ throw Exception()
    }

    override fun copy(newName: String): Variable {
        return NullVariable(newName)
    }

}