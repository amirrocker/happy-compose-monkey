package de.amirrocker.happycomposemonkey.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import de.amirrocker.happycomposemonkey.R
import de.amirrocker.happycomposemonkey.ui.theme.compositedOnSurface


/**
 * Wrap this class around [Image] and [rememberImagePainter], setting [contentScale] to default
 * and showing a placeholder content while loading the [_root_ide_package_.de.amirrocker.happycomposemonkey.presentation.utils.NetworkImage(url = "https://example.com/")].
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.3f)
) {

    Box(modifier = Modifier.padding(8.dp)) {
        val painter = rememberImagePainter(
            data = url,
            builder = {
                placeholder(R.drawable.ic_launcher_background)
            }
        )
        Image(
            painter=painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.fillMaxWidth()
        )

        if(painter.state is ImagePainter.State.Loading && placeholderColor != null) {
            Spacer(modifier = Modifier
                .matchParentSize()
                .background(placeholderColor)
            )
        }
    }

}