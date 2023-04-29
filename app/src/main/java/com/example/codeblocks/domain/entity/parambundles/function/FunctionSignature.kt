package com.example.codeblocks.domain.entity.parambundles.function

import com.example.codeblocks.domain.entity.ParamBundle
import com.example.codeblocks.domain.entity.Variable
import kotlin.reflect.KClass

class FunctionSignature(val name: String, val returnType: KClass<out Variable>) : ParamBundle() {

    private val _paramsSignature: MutableList<Pair<String, KClass<out Variable>>> = mutableListOf()
    val paramsSignature: List<Pair<String, KClass<out Variable>>> = _paramsSignature

    fun addParam(name: String, type: KClass<out Variable>) {
        _paramsSignature.add(Pair(name, type))
    }

}