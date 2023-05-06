package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class BooleanVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Boolean::class
    private var value: Boolean? = null

    override fun getValue(): Boolean? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Boolean?
    }

    override fun copy(newName: String): Variable {
        val newVariable = BooleanVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}