package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class LongVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Long::class
    private var value: Long? = null

    init {
        value = 0
    }

    override fun getValue(): Long? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Long?
    }

    override fun copy(newName: String): Variable {
        val newVariable = LongVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}