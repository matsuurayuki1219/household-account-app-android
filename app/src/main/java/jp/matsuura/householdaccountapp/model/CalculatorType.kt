package jp.matsuura.householdaccountapp.model

sealed class CalculatorType {
    data class Number(val number: String) : CalculatorType()
    object Plus : CalculatorType()
    object Minus : CalculatorType()
    object Multiplication : CalculatorType()
    object Division : CalculatorType()
    object DecimalPoint : CalculatorType()
}