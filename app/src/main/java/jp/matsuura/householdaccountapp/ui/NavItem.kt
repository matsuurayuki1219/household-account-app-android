package jp.matsuura.householdaccountapp.ui

sealed class NavItem (
    val title: String,
    val route: String,
) {
    object InputMoneyScreen: NavItem(title = "入力画面", route = "input_money")
}