package de.amirrocker.happycomposemonkey.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SignIn(
    onSignInComplete: (Boolean)->Unit
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var staySignedIn by remember { mutableStateOf(false) }

    Surface(modifier = Modifier
        .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        SignInContent(
            username = username,
            usernameChanged = {
                username = it
            },

            password = password,
            passwordChanged = {
                password = it
            },

            staySignedIn = staySignedIn,
            staySignedInChanged = {
                staySignedIn = it
            },

            onSignInComplete = onSignInComplete
        )
    }

}

@Preview
@Composable
fun PreviewSignIn() {
    SignInContent(usernameChanged = {
        println(it)
    },
        passwordChanged = {
            println(it)
        },
        staySignedInChanged = {
            println(it)
        },
        onSignInComplete = { println("complete") }
    )
}

@Composable
fun UsernameInput(username:String, usernameChanged: (String) -> Unit) {
    TextField(
        value=username,
        onValueChange = usernameChanged,
        modifier = Modifier
            .padding(horizontal = 8.dp),
        placeholder = { "Enter Username" },
        label = { Text(text = "Bitte username eintragen") }
    )
}

@Composable
fun PasswordInput(password:String, passwordChanged: (String) -> Unit) {
    TextField(
        value=password,
        onValueChange =  passwordChanged,
        modifier = Modifier
            .padding(horizontal = 8.dp),
        placeholder = { "Enter Password" },
        trailingIcon = { Icon(Icons.Filled.Add, "Add Icon - should be username")},
        label = { Text(text = "Bitte Passwort eintragen")}
    )
}

@Composable
fun StaySignedIn(staySignedIn: Boolean, staySignedInChanged: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Stay signed in",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Switch(
            checked = staySignedIn,
            onCheckedChange = staySignedInChanged
        )
    }
}

@Composable
fun SignInButton(onClick: ()->Unit, buttonLabel:String = "SignIn") {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = buttonLabel)
    }
}

@Composable
fun SignInContent(
        username: String = "",
        usernameChanged:(String)->Unit,
        password: String = "",
        passwordChanged:(String)->Unit,
        staySignedIn:Boolean = false,
        staySignedInChanged:(Boolean)->Unit = {},
        onSignInComplete: (Boolean)->Unit
) {

    Column(modifier = Modifier.padding(8.dp)) {
        UsernameInput(
            username = username,
            usernameChanged = usernameChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInput(
            password = password,
            passwordChanged = passwordChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        StaySignedIn(
            staySignedIn = staySignedIn,
            staySignedInChanged = staySignedInChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        SignInButton(onClick = {
            println("Sign In clicked")
            onSignInComplete(true)
        })
    }
}