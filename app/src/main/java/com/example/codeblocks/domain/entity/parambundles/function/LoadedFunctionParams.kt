package com.example.codeblocks.domain.entity.parambundles.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable

class LoadedFunctionParams : ParamBundle() {

    private val _variableList: MutableList<Variable> = mutableListOf()
    val variableList: List<Variable> = _variableList

    fun addParam(variable: Variable) {
        _variableList.add(variable)
    }

}