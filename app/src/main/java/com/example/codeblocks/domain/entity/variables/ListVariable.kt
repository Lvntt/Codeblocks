package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.DefaultValues
import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

data class MutableListValue(
    val elementType: KClass<out Variable>,
    val list: MutableList<Variable> = mutableListOf()
) {
    override fun toString(): String {
        return if(elementType != CharVariable::class) {
            list.toString()
        } else {
            list.joinToString(DefaultValues.EMPTY_STRING) { it.toString() }
        }
    }
}

open class ListVariable(name: String, val elementType: KClass<out Variable>) : Variable(name) {

    override val valueType: KClass<out Any> = MutableListValue::class
    protected var value: MutableListValue = MutableListValue(elementType)

    override fun copy(newName: String): Variable {
        val newVariable = ListVariable(newName, elementType)
        newVariable.setValue(value)
        return newVariable
    }

    override fun getValue(): Any = value

    override fun setValue(value: Any?) {
        if (value == null) { /*TODO error handling*/ throw Exception() }
        if (value::class != valueType) { /*TODO error handling*/ throw Exception() }
        if (((value as MutableListValue).elementType) != elementType) { /*TODO error handling*/ throw Exception() }
        setValueAfterChecks(value)
    }

    override fun setValueAfterChecks(value: Any?) {
        this.value = value as MutableListValue
    }

    fun setElement(variable: Variable, index: Int) {
        if (variable::class != elementType) { /*TODO error handling*/ throw Exception() }
        if (index >= value.list.size || index < 0) { /*TODO error handling*/ throw Exception() }
        value.list[index] = variable.copy(variable.name)
    }

    fun addElement(variable: Variable) {
        if (variable::class != elementType) { /*TODO error handling*/ throw Exception() }
        value.list.add(variable.copy(variable.name))
    }

    fun removeElement(index: Int) {
        if (index >= value.list.size || index < 0) { /*TODO error handling*/ throw Exception() }
        value.list.removeAt(index)
    }

    fun insertElement(variable: Variable, index: Int) {
        if (variable::class != elementType) { /*TODO error handling*/ throw Exception() }
        if (index > value.list.size || index < 0) { /*TODO error handling*/ throw Exception() }
        value.list.add(index, variable.copy(variable.name))
    }

    fun getElement(index: Int): Variable {
        if (index >= value.list.size || index < 0) { /*TODO error handling*/ throw Exception() }
        return value.list[index]
    }

}