package jp.matsuura.householdaccountapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavScreen (
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    object Home : BottomBarNavScreen("Home", Icons.Default.Home,"home")
    object History: BottomBarNavScreen("History", Icons.Default.Home,"history")
    object Analyze: BottomBarNavScreen("Analyze", Icons.Default.Home,"analyze")
}