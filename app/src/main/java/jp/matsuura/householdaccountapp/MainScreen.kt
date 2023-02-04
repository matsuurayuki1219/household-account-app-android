package jp.matsuura.householdaccountapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
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
            NavScreen.InputMoneyScreen.route -> {
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
            BottomBarGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    val screens = listOf(
        BottomBarNavScreen.Home,
        BottomBarNavScreen.History,
        BottomBarNavScreen.Analyze,
    )
    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarNavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}