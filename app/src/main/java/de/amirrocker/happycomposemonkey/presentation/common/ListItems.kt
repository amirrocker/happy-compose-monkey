package de.amirrocker.happycomposemonkey.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.OndemandVideo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.model.Emergency
import de.amirrocker.happycomposemonkey.model.courses
import de.amirrocker.happycomposemonkey.presentation.utils.NetworkImage
import de.amirrocker.happycomposemonkey.ui.theme.HappyComposeMonkeyTheme

@Composable
fun CourseListItem(
    emergency: Emergency,
    clickCourse: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = HappyComposeMonkeyTheme.elevation.card,
    titleStyle: TextStyle = MaterialTheme.typography.body1,
    iconSize: Dp = 16.dp
) {

    Surface(
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.clickable(onClick = clickCourse)
        ) {
            NetworkImage(
                url = emergency.thumbUrl,
                modifier = Modifier.aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    )
            ) {
                Text(
                    text = emergency.name,
                    style = titleStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.OndemandVideo,
                        contentDescription = "",
                        modifier = Modifier.size(iconSize)
                    )
                    Text(
                        text = stringResource(id = R.string.course_step_steps, emergency.step, emergency.steps),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                    )
                    NetworkImage(
                        url = emergency.instructor,
                        contentDescription = "Instructor Image",
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CourseListItemPreview() {
    CourseListItem(
        emergency = courses.first(),
        clickCourse = {
            println("list item clicked")
        }
    )
}
