package de.amirrocker.happycomposemonkey.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Start() {
    val header by remember { mutableStateOf("Start View") }
    StartContent(header)
}

@Composable
fun StartContent(header:String) {

    Surface(modifier = Modifier.padding(start=8.dp, top=64.dp, end=8.dp, bottom=8.dp)) {
        Column {
            Text(text = header,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Another Text line",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun StartPreview() {
    Start()
}
