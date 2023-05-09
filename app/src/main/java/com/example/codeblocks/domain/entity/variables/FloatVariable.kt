package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class FloatVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Float::class
    private var value: Float? = null

    override fun getValue(): Float? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Float?
    }

    override fun copy(newName: String): Variable {
        val newVariable = FloatVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}