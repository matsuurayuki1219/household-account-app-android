package jp.matsuura.householdaccountapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jp.matsuura.householdaccountapp.ui.analyze.AnalyzeScreen
import jp.matsuura.householdaccountapp.ui.history.HistoryScreen
import jp.matsuura.householdaccountapp.ui.home.HomeScreen
import jp.matsuura.householdaccountapp.ui.input_money.InputMoneyScreen

@Composable
fun BottomBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavScreen.Home.route,
    ) {
        composable(route = BottomBarNavScreen.Home.route) {
            HomeScreen(
                navigateToInputScreen = { categoryId, categoryName ->
                    navController.navigate(NavScreen.InputMoneyScreen.route)
                }
            )
        }
        composable(route = BottomBarNavScreen.History.route) {
            HistoryScreen()
        }
        composable(route = BottomBarNavScreen.Analyze.route) {
            AnalyzeScreen()
        }
        composable(route = NavScreen.InputMoneyScreen.route) {
            InputMoneyScreen()
        }
    }
}