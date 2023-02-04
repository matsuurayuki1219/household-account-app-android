package jp.matsuura.householdaccountapp

import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen (
    val title: String,
    val route: String,
) {
    object InputMoneyScreen: NavScreen(title = "入力画面", route = "input_money")
}