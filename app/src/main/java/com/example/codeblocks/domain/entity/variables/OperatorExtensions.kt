package com.example.codeblocks.domain.entity.variables

object OperatorExtensions {
    //All the operators should be declared here
    //Plus operator - plusOperator(other) extension
    //Minus operator - minusOperator(other) extension
    //Multiplication operator - timesOperator(other) extension
    //Division operator - divOperator(other) extension
    //Remainder operator - remOperator(other) extension

    /*Int operators*/
    fun Int.plusOperator(other: Byte): Int =
        this + other.toInt()

    fun Int.plusOperator(other: Short): Int =
        this + other.toInt()

    fun Int.plusOperator(other: Int): Int =
        this + other

    fun Int.plusOperator(other: Long): Long =
        this.toLong() + other

    fun Int.plusOperator(other: Float): Float =
        this.toFloat() + other

    fun Int.plusOperator(other: Double): Double =
        this.toDouble() + other


    fun Int.minusOperator(other: Byte): Int =
        this - other.toInt()

    fun Int.minusOperator(other: Short): Int =
        this - other.toInt()

    fun Int.minusOperator(other: Int): Int =
        this - other

    fun Int.minusOperator(other: Long): Long =
        this.toLong() - other

    fun Int.minusOperator(other: Float): Float =
        this.toFloat() - other

    fun Int.minusOperator(other: Double): Double =
        this.toDouble() - other


    fun Int.timesOperator(other: Byte): Int =
        this * other.toInt()

    fun Int.timesOperator(other: Short): Int =
        this * other.toInt()

    fun Int.timesOperator(other: Int): Int =
        this * other

    fun Int.timesOperator(other: Long): Long =
        this.toLong() * other

    fun Int.timesOperator(other: Float): Float =
        this.toFloat() * other

    fun Int.timesOperator(other: Double): Double =
        this.toDouble() * other


    fun Int.divOperator(other: Byte): Int =
        this / other.toInt()

    fun Int.divOperator(other: Short): Int =
        this / other.toInt()

    fun Int.divOperator(other: Int): Int =
        this / other

    fun Int.divOperator(other: Long): Long =
        this.toLong() / other

    fun Int.divOperator(other: Float): Float =
        this.toFloat() / other

    fun Int.divOperator(other: Double): Double =
        this.toDouble() / other


    fun Int.remOperator(other: Byte): Int =
        this % other.toInt()

    fun Int.remOperator(other: Short): Int =
        this % other.toInt()

    fun Int.remOperator(other: Int): Int =
        this * other

    fun Int.remOperator(other: Long): Long =
        this.toLong() % other

    fun Int.remOperator(other: Float): Float =
        this.toFloat() % other

    fun Int.remOperator(other: Double): Double =
        this.toDouble() % other
}