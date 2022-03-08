package de.amirrocker.happycomposemonkey.presentation.emergencies

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.amirrocker.happycomposemonkey.MainDestinations
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.model.TopicRepo
import de.amirrocker.happycomposemonkey.model.courses


fun NavGraphBuilder.emergencies(
    onCourseSelected: (Long, NavBackStackEntry) -> Unit,
    onboardingComplete: State<Boolean>,
    signInComplete: State<Boolean>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    composable(EmergencyTabs.CURRENT.route) { from ->
        LaunchedEffect(key1 = onboardingComplete, key2 = signInComplete) {
            if (!signInComplete.value) {
                navController.navigate(MainDestinations.SIGNIN_ROUTE)
            }
            if(!onboardingComplete.value) {
                navController.navigate(MainDestinations.ONBOARDING_ROUTE)
            }
        }
        if(onboardingComplete.value && signInComplete.value ) {
            FeaturedEmergencies(
                courses = courses,
                selectCourse = { id -> onCourseSelected(id, from) }
            )
        }
    }
    composable(EmergencyTabs.MY_EMERGENCIES.route) { from ->
        MyEmergencies(
            courses = courses,
            selectCourse = { id ->
                onCourseSelected(id, from)
            }
        )
    }
    composable(EmergencyTabs.SEARCH.route) { from ->
        SearchEmergencies(
            topics = TopicRepo.getTopics(), modifier=modifier
        )
    }


}

enum class EmergencyTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
    ) {
    MY_EMERGENCIES(R.string.my_courses, R.drawable.ic_launcher_background, CoursesDestinations.MY_EMERGENCIES_ROUTE),
    CURRENT(R.string.featured, R.drawable.ic_launcher_background, CoursesDestinations.CURRENT_EMERGENCIES_ROUTE),
    SEARCH(R.string.search, R.drawable.ic_launcher_background, CoursesDestinations.SEARCH_EMERGENCIES_ROUTE)
}

private object CoursesDestinations {
    // featured becomes current
    const val CURRENT_EMERGENCIES_ROUTE = "emergencies/current"
    const val MY_EMERGENCIES_ROUTE = "emergencies/my"
    const val SEARCH_EMERGENCIES_ROUTE = "emergencies/search"
}

