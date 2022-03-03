package de.amirrocker.happycomposemonkey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import de.amirrocker.happycomposemonkey.presentation.Onboarding
import de.amirrocker.happycomposemonkey.presentation.SignIn
import de.amirrocker.happycomposemonkey.presentation.Start
import de.amirrocker.happycomposemonkey.ui.theme.HappyComposeMonkeyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            HappyComposeMonkeyTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
////                    Greeting("Android")
//                    NavHost(navController = navC, graph = )
//                }
//            }
//        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            HappyComposeMonkeyApp {
                finish()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HappyComposeMonkeyTheme {
        //Greeting("Android")
//        SignIn()
//        Onboarding {
//            println("onboarding complete ....")
//        }
//        Start()
        HappyComposeMonkeyApp {}
    }
}