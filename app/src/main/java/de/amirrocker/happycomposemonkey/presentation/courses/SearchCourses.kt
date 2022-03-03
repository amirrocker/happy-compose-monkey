package de.amirrocker.happycomposemonkey.presentation.courses

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.model.Topic
import de.amirrocker.happycomposemonkey.model.TopicRepo

@Composable
fun SearchCourses(
    topics: List<Topic>,
    modifier: Modifier = Modifier
) {
    // Note this interesting piece of code!!
    val (searchTerm, updateSearchTerm) = remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }
    // statusBarsPadding takes into account the status bar when the calculating height
    LazyColumn(modifier = modifier.statusBarsPadding()) {

        item { AppBar(searchTerm = searchTerm, updateSearchTerm = updateSearchTerm) }
        val filteredTopics = getTopics(searchTerm, topics)
        items(filteredTopics) { topic ->
            Text(
                text = topic.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { println("clicked topic $topic") })
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .wrapContentWidth(Alignment.Start)
            )
        }
    }
}


private fun getTopics(searchTerm: TextFieldValue, topics:List<Topic>): List<Topic> =
        topics.filter { topic -> searchTerm.text.lowercase() in topic.name.lowercase() }

/**
 * AppBar is a TopBar containing the search Textinput field represented by the
 * [TextFieldValue] construct.
 */
@Composable
fun AppBar(
    searchTerm: TextFieldValue,
    updateSearchTerm: (TextFieldValue) -> Unit
) {
    TopAppBar(elevation = 0.dp) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)
        )
        BasicTextField(
            value = searchTerm,
            onValueChange = updateSearchTerm,
            textStyle = MaterialTheme.typography.subtitle1.copy(
                color = LocalContentColor.current
            ),
            maxLines = 1,
            cursorBrush = SolidColor(LocalContentColor.current),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(
            onClick = { println("account profile button clicked") },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    SearchCourses(
        topics = TopicRepo.getTopics(),
        modifier = Modifier
    )
}

