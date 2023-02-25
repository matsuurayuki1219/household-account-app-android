package jp.matsuura.householdaccountapp.ui.home

sealed interface HomeScreenEvent {
    data class NavigateToInputScreen(val categoryId: Int) : HomeScreenEvent
}