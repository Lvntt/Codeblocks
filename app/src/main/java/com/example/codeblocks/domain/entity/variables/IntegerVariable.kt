package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class IntegerVariable(name: String): Variable(name) {
    override val valueType: KClass<out Any> = Integer::class
    private var value: Int? = null

    override fun getValue(): Int? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Int?
    }

    override fun copy(newName: String): Variable {
        val newVariable = IntegerVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }
}