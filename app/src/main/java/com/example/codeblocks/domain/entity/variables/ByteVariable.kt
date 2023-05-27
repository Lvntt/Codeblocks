package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class ByteVariable(name: String) : Variable(name) {

    override val valueType: KClass<out Any> = Byte::class
    private var value: Byte? = null

    init {
        value = 0
    }

    override fun getValue(): Byte? = value

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as Byte?
    }

    override fun copy(newName: String): Variable {
        val newVariable = ByteVariable(newName)
        newVariable.setValue(value)
        return newVariable
    }

}