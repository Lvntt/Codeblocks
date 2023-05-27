package com.example.codeblocks.domain.entity

interface Returnable {

    suspend fun getReturnedValue(): Variable?

}