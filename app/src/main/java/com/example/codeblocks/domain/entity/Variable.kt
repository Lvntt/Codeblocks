package com.example.codeblocks.domain.entity

abstract class Variable(private val name: String) {

    abstract fun copy(newName: String): Variable

    fun getName(): String = name

}