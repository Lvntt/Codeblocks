package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class DoubleVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Double::class
    private var value: Double? = null

    init {
        value = 0.0
    }

    override fun getValue(): Double? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Double?
    }

    override fun copy(newName: String): Variable {
        val newVariable = DoubleVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}