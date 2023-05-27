package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class ShortVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Short::class
    private var value: Short? = null

    init {
        value = 0
    }

    override fun getValue(): Short? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Short?
    }

    override fun copy(newName: String): Variable {
        val newVariable = ShortVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}