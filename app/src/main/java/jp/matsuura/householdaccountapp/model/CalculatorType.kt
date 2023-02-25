package jp.matsuura.householdaccountapp.model

sealed class CalculatorType {
    data class Number(val number: String) : CalculatorType()
    object Del: CalculatorType()
}