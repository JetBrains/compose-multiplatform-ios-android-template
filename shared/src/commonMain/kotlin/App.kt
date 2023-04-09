import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
internal fun App() {
    var currentScreen: Screen by rememberSaveable { mutableStateOf(Screen.HelloWorld) }

    MaterialTheme {
        when (currentScreen) {
            Screen.HelloWorld -> HelloWorldScreen(
                onButtonClick = { currentScreen = Screen.SimpleViewModel }
            )

            Screen.SimpleViewModel -> SimpleScreen(
                backAction = { currentScreen = Screen.HelloWorld }
            )
        }
    }
}

expect fun getPlatformName(): String
