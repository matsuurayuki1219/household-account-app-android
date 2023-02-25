package jp.matsuura.householdaccountapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.matsuura.householdaccountapp.ui.analyze.AnalyzeScreen
import jp.matsuura.householdaccountapp.ui.history.HistoryScreen
import jp.matsuura.householdaccountapp.ui.home.HomeScreen
import jp.matsuura.householdaccountapp.ui.input_money.InputMoneyScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomNavItem.History.route) {
            HistoryScreen()
        }
        composable(route = BottomNavItem.Analyze.route) {
            AnalyzeScreen()
        }
        composable(
            route = NavItem.InputMoneyScreen.route + "/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) {
            InputMoneyScreen(navController = navController)
        }
    }
}