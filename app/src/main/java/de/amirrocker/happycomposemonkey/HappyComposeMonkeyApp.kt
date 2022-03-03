package de.amirrocker.happycomposemonkey

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import de.amirrocker.happycomposemonkey.presentation.courses.CourseTabs
import de.amirrocker.happycomposemonkey.ui.theme.HappyComposeMonkeyTheme
import java.util.*

@Composable
fun HappyComposeMonkeyApp(finishActivity: () -> Unit) {
    ProvideWindowInsets {
        HappyComposeMonkeyTheme {
            val tabs = remember { CourseTabs.values() }
            val navController = rememberNavController()

            Scaffold(
                backgroundColor = MaterialTheme.colors.primarySurface,
                bottomBar = { HappyComposeMonkeyBottomBar(navController = navController, tabs) }
            ) { innerPaddingsModifier ->
                NavGraph(
                    finishActivity = finishActivity,
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingsModifier)
                )
            }
        }
    }
}

@Composable
fun HappyComposeMonkeyBottomBar(
    navController: NavController,
    tabs: Array<CourseTabs>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
        ?: CourseTabs.FEATURED.route

    val routes = remember {
        CourseTabs.values().map { it.route }
    }

    if (currentRoute in routes) {
        BottomNavigation(Modifier.navigationBarsHeight(additional = 56.dp)) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = tab.icon), contentDescription = null) },
                    label = { Text(text = stringResource(tab.title).uppercase(Locale.getDefault())) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route not currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = true,
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = LocalContentColor.current,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}

infix fun String.not(another: String) = this != another






/*
Trashbin:

//            Scaffold(
//                backgroundColor = MaterialTheme.colors.primarySurface,
//                bottomBar = {
//                    BottomNavigation {
//                        val navBackStackEntry by navController.currentBackStackEntryAsState()
//                        val currentDestination = navBackStackEntry?.destination
//                        tabs.forEach { tab ->
//                            BottomNavigationItem(
//                                icon = { Icon(painterResource(id = tab.icon), contentDescription = null) },
//                                label = { Text(text = stringResource(tab.title).uppercase(Locale.getDefault())) },
//                                selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
//                                onClick = {
//
//                                    navController.navigate(tab.route) {
//                                        popUpTo(navController.graph.startDestinationId) {
//                                            saveState = true
//                                        }
//                                        launchSingleTop = true
//                                        restoreState = true
//                                    }
//
////                                    if (tab.route not currentDestination?.route) {
////                                        navController.navigate(tab.route) {
////                                            popUpTo(navController.graph.startDestinationId) {
////                                                saveState = true
////                                            }
////                                            launchSingleTop = true
////                                            restoreState = true
////                                        }
////                                    }
//                                },
//                                alwaysShowLabel = true,
//                                selectedContentColor = MaterialTheme.colors.secondary,
//                                unselectedContentColor = LocalContentColor.current,
//                                modifier = Modifier.navigationBarsPadding()
//                            )
//                        }
//                    }
//                }
//            ) { innerPaddingsModifier ->
//                NavGraph(
//                    finishActivity = finishActivity,
//                    navController = navController,
//                    modifier = Modifier.padding(innerPaddingsModifier)
//                )
//
//        /*{ innerPaddings ->
//                NavHost(navController = navController, startDestination = MainDestinations.START_ROUTE, Modifier.padding(innerPaddings) ) {
//                    composable(MainDestinations.START_ROUTE) { Start() }
//                    composable(MainDestinations.SIGNIN_ROUTE) { SignIn() }
//                    composable(MainDestinations.ONBOARDING_ROUTE) { Onboarding({println("Onboarding finished")}) }
//                }
//            */
//
//            }

 */

