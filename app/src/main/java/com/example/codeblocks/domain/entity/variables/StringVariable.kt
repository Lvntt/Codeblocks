package com.example.codeblocks.domain.entity.variables

import com.example.codeblocks.domain.entity.DefaultValues

class StringVariable(name: String): ListVariable(name, CharVariable::class) {

    override fun toString(): String {
        return (getValue() as MutableListValue).list.joinToString(DefaultValues.EMPTY_STRING) { it.toString() }
    }

    fun setStringValue(string: String) {
        val newValue = MutableListValue(CharVariable::class)
        string.toCharArray().forEach {
            val newChar = CharVariable(DefaultValues.EMPTY_STRING)
            newChar.setValue(it)
            newValue.list.add(newChar)
        }
        value = newValue
    }

}