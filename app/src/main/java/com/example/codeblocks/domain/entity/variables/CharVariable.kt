package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class CharVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Char::class
    private var value: Char? = null

    override fun getValue(): Char? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Char?
    }

    override fun copy(newName: String): Variable {
        val newVariable = CharVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}