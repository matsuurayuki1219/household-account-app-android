package jp.matsuura.householdaccountapp.ui

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jp.matsuura.householdaccountapp.ui.theme.HouseHoldAccountAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    HouseHoldAccountAppTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        // State of bottomBar, set state to false, if current page route is "car_details"
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        when (navBackStackEntry?.destination?.route) {
            NavItem.InputMoneyScreen.route -> {
                bottomBarState.value = false
            }
            else -> {
                bottomBarState.value = true
            }
        }

        // A surface container using the 'background' color from the theme
        Scaffold(
            bottomBar = { if (bottomBarState.value) BottomBar(navController = navController, currentDestination = currentDestination) },
        ) {
            NavGraph(navController = navController)
        }
    }
}