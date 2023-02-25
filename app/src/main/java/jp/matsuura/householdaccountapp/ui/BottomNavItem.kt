package jp.matsuura.householdaccountapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem (
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    object Home : BottomNavItem("Home", Icons.Default.Home,"home")
    object History: BottomNavItem("History", Icons.Default.Home,"history")
    object Analyze: BottomNavItem("Analyze", Icons.Default.Home,"analyze")
}