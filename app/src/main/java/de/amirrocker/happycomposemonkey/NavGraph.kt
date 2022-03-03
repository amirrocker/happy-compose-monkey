package de.amirrocker.happycomposemonkey

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import de.amirrocker.happycomposemonkey.MainDestinations.COURSE_DETAIL_ID_KEY
import de.amirrocker.happycomposemonkey.presentation.Onboarding
import de.amirrocker.happycomposemonkey.presentation.SignIn
import de.amirrocker.happycomposemonkey.presentation.Start
import de.amirrocker.happycomposemonkey.presentation.course.CourseDetails
import de.amirrocker.happycomposemonkey.presentation.courses.CourseTabs
import de.amirrocker.happycomposemonkey.presentation.courses.courses

@Composable
fun NavGraph(
    modifier:Modifier = Modifier,
    finishActivity: ()->Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.COURSES_ROUTE,
    showOnBoardingInitially: Boolean = true,
    showSignInInitially: Boolean = true
) {
    // will be read from datastore in v2
    val onBoardingComplete = remember(showOnBoardingInitially) {
        mutableStateOf(!showOnBoardingInitially)
    }

    // will be read from datastore in v2
    val onSignInComplete = remember(showSignInInitially) {
        mutableStateOf(!showSignInInitially)
    }

    val actions = remember(navController) {
        MainActions(navController)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // show Onboarding
        composable(MainDestinations.ONBOARDING_ROUTE) {
            BackHandler {
                finishActivity()
            }
            Onboarding(onboardingComplete = {
                println("Onboarding finished")
                onBoardingComplete.value = true
                actions.onOnboardingComplete()
            })
        }

        // show SignIn
        composable(MainDestinations.SIGNIN_ROUTE) {
            BackHandler {
                finishActivity()
            }
            SignIn(
                onSignInComplete = { isSignedIn ->
                    println("SignIn finished with result: $isSignedIn")
                    onSignInComplete.value = true
                    actions.onSignInComplete()
                }
            )
        }

        // show a bottom bar with three 'views'
        navigation(
            route=MainDestinations.COURSES_ROUTE,
            startDestination = CourseTabs.FEATURED.route
        ) {
            courses(
                onCourseSelected = actions.openCourse,
                onboardingComplete = onBoardingComplete,
                signInComplete = onSignInComplete,
                modifier = modifier,
                navController = navController
            )
        }

        composable(
            route = "${MainDestinations.COURSE_DETAIL_ROUTE}/{$COURSE_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(COURSE_DETAIL_ID_KEY) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val currentCourseId = arguments.getLong(COURSE_DETAIL_ID_KEY)
            println("display current course for Id: $currentCourseId")
            CourseDetails(
                courseId = currentCourseId,
                selectCourse = { newCourseId:Long ->
                    actions.relatedCourse(newCourseId, navBackStackEntry)
                },
                upPress = {
                    actions.upPress(navBackStackEntry)
                }
            )
        }
    }
}

class MainActions(navController: NavHostController) {

    // TODO use datastore
    // once onboarding is complete it gets pop'd of the stack
    val onOnboardingComplete : () -> Unit = {
        // remember to reset the datastore
        navController.popBackStack()
    }

    // what action to take after login is success
    val onSignInComplete : () -> Unit = {
        // remember to reset the datastore
        navController.popBackStack()
        navController.navigate(MainDestinations.COURSES_ROUTE)
    }

    // what action to take after login is Failed
    val onSignInFailed : () -> Unit = {
        // remember to reset the datastore
        navController.popBackStack()
    }

    // the main course route
    val openCourse = { newCourseId:Long, from: NavBackStackEntry ->
        // always check the lifecycle to avoid duplicate events
        if(from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.COURSE_DETAIL_ROUTE}/$newCourseId")
        }
    }

    val relatedCourse = { newCourseId: Long, from: NavBackStackEntry ->
        // always check the lifecycle to avoid duplicate events
        if(from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.COURSE_DETAIL_ROUTE}/$newCourseId")
        }
    }

    val signIn = { from: NavBackStackEntry ->
        // always check the lifecycle to avoid duplicate events
        if(from.lifecycleIsResumed()) {
            navController.navigate(MainDestinations.SIGNIN_ROUTE)
        }
    }

    val upPress = { from: NavBackStackEntry ->
        // always check the lifecycle to avoid duplicate events
        if( from.lifecycleIsResumed() ) {
            navController.navigateUp()
        }
    }


}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

/**
 * Destinations used in the App.
 */
object MainDestinations {
//    const val ONBOARDING_ROUTE = "onboarding"
//    const val START_ROUTE = "start"
//    const val COURSES_ROUTE = "courses"
//    const val COURSE_DETAIL_ROUTE = "course"
//
    const val COURSE_DETAIL_ID_KEY = "courseId"
    const val ONBOARDING_ROUTE = "onboarding"
    const val COURSES_ROUTE = "courses"
    const val COURSE_DETAIL_ROUTE = "course"

    const val SIGNIN_ROUTE = "signin"
}