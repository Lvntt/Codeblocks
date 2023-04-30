package com.example.codeblocks.domain.entity

abstract class Variable(val name: String): Returnable {

    //ANY VARIABLE CLASS(except null) SHOULD HAVE setValue and getValue METHODS
    //Any variable can also implement different operators via functions like plus, minus, div, rem, times, equals, etc.
    abstract fun copy(newName: String): Variable

    override fun getReturnedValue(): Variable? = this

    abstract override fun toString(): String
}