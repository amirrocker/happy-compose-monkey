package de.amirrocker.happycomposemonkey.presentation.emergencies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.model.Emergency
import de.amirrocker.happycomposemonkey.model.courses
import de.amirrocker.happycomposemonkey.ui.theme.HappyComposeMonkeyTheme

@Composable
fun FeaturedEmergencies(
    courses: List<Emergency>,
    selectCourse: (Long)->Unit,
    modifier:Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .statusBarsPadding()
    ) {
        item {
            EmergenciesAppBar()
        }
        items(courses) { course ->
            FeaturedEmergency(
                emergency = course,
                selectCourse = selectCourse
            )
        }
    }
}

@Composable
fun FeaturedEmergency(
    emergency: Emergency,
    selectCourse: (Long)->Unit,
    modifier:Modifier = Modifier
) {

    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = HappyComposeMonkeyTheme.elevation.card,
        shape = MaterialTheme.shapes.medium
    ) {
        val featuredString = stringResource(id = R.string.value_d)

        ConstraintLayout(
            modifier = Modifier
                .clickable {
                    selectCourse(emergency.courseId)
                }
                .semantics {
                    contentDescription = featuredString
                }
        )
        {
            val (name) = createRefs()
            Text(
                text=emergency.name,
                style = MaterialTheme.typography.subtitle1,
                modifier= modifier
                    .padding(8.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )
        }

    }
}

@Preview
@Composable
fun FeaturedCoursePreview() {
    HappyComposeMonkeyTheme {
        FeaturedEmergency(
            emergency = courses.first(),
            selectCourse = { }
        )
    }
}

@Preview
@Composable
fun FeaturedCoursesPreview() {
    FeaturedEmergencies(
        courses = courses,
        selectCourse = {} )
}


