package jp.matsuura.householdaccountapp.ui.home

import jp.matsuura.householdaccountapp.model.CategoryModel

data class HomeScreenState (
    val isProgressVisible: Boolean,
    val category: List<CategoryModel>,
){
    companion object {
        val initValue = HomeScreenState(
            isProgressVisible = false,
            category = emptyList(),
        )
    }
}