package com.example.codeblocks.domain.entity

class FunctionParamValues : ParamBundle() {

    private val _variableList: MutableList<Variable> = mutableListOf()
    val variableList: List<Variable> = _variableList

    fun addParam(variable: Variable) {
        _variableList.add(variable)
    }

}