package de.amirrocker.happycomposemonkey.presentation.courses

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
import de.amirrocker.happycomposemonkey.presentation.MyCourses


fun NavGraphBuilder.courses(
    onCourseSelected: (Long, NavBackStackEntry) -> Unit,
    onboardingComplete: State<Boolean>,
    signInComplete: State<Boolean>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    composable(CourseTabs.FEATURED.route) { from ->
        LaunchedEffect(key1 = onboardingComplete, key2 = signInComplete) {
            if (!signInComplete.value) {
                navController.navigate(MainDestinations.SIGNIN_ROUTE)
            }
            if(!onboardingComplete.value) {
                navController.navigate(MainDestinations.ONBOARDING_ROUTE)
            }
        }
        if(onboardingComplete.value && signInComplete.value ) {
            FeaturedCourses(
                courses = courses,
                selectCourse = { id -> onCourseSelected(id, from) }
            )
        }
    }
    composable(CourseTabs.MY_COURSES.route) { from ->
        MyCourses(
            courses = courses,
            selectCourse = { id ->
                onCourseSelected(id, from)
            }
        )
    }
    composable(CourseTabs.SEARCH.route) { from ->
        SearchCourses(
            topics = TopicRepo.getTopics(), modifier=modifier
        )
    }


}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
    ) {
    MY_COURSES(R.string.my_courses, R.drawable.ic_launcher_background, CoursesDestinations.MY_COURSES_ROUTE),
    FEATURED(R.string.featured, R.drawable.ic_launcher_background, CoursesDestinations.FEATURED_ROUTE),
    SEARCH(R.string.search, R.drawable.ic_launcher_background, CoursesDestinations.SEARCH_COURSES_ROUTE)
}

private object CoursesDestinations {
    const val FEATURED_ROUTE = "courses/featured"
    const val MY_COURSES_ROUTE = "courses/my"
    const val SEARCH_COURSES_ROUTE = "courses/search"
}

