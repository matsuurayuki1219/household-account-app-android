package jp.matsuura.householdaccountapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jp.matsuura.householdaccountapp.ui.analyze.AnalyzeScreen
import jp.matsuura.householdaccountapp.ui.history.HistoryScreen
import jp.matsuura.householdaccountapp.ui.home.HomeScreen

@Composable
fun BottomBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavScreen.Home.route,
    ) {
        composable(route = BottomBarNavScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarNavScreen.History.route) {
            HistoryScreen()
        }
        composable(route = BottomBarNavScreen.Analyze.route) {
            AnalyzeScreen()
        }
    }
}