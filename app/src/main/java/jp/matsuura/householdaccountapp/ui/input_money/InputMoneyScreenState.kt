package jp.matsuura.householdaccountapp.ui.input_money

import jp.matsuura.householdaccountapp.model.CategoryModel

data class InputMoneyScreenState(
    val isProgressVisible: Boolean,
    val selectedCategory: CategoryModel?,
    val totalMoneyAmount: Int,
){
    companion object {
        val initValue = InputMoneyScreenState(
            isProgressVisible = false,
            selectedCategory = null,
            totalMoneyAmount = 0,
        )
    }
}