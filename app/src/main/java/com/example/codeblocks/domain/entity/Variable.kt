package com.example.codeblocks.domain.entity

abstract class Variable(val name: String): Returnable {

    //ANY VARIABLE CLASS(except null) SHOULD HAVE setValue and getValue METHODS
    abstract fun copy(newName: String): Variable

    override fun getReturnedValue(): Variable? = this

    abstract override fun toString(): String
}