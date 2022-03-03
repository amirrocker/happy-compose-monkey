package de.amirrocker.happycomposemonkey.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun EditText(placeholder:String="") {

    var text by rememberSaveable { mutableStateOf("") }
    var header by rememberSaveable { mutableStateOf(placeholder) }

    Column {
        Text(
            text=header,
            modifier=Modifier.padding(4.dp)
        )
        TextField(value = text, onValueChange = {
            text = it
        })
    }
}

@Preview
@Composable
fun EditTextPreview() {
    EditText("Placeholder Dummy")
}


