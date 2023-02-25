package jp.matsuura.householdaccountapp.ui.input_money

sealed interface InputMoneyScreenEvent {
    data class SaveTransactionSuccess(val categoryName: String, val totalMoney: Int) : InputMoneyScreenEvent
    data class Error(val error: Throwable) : InputMoneyScreenEvent
}