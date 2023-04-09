import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun App() {
    var currentScreen: Route by rememberSaveable { mutableStateOf(Route.Welcome) }

    MaterialTheme {
        when (currentScreen) {
            Route.Welcome -> WelcomeScreen(
                route = { currentScreen = it }
            )

            Route.Resources -> ResourcesScreen(
                backAction = { currentScreen = Route.Welcome }
            )

            Route.ViewModel -> ViewModelScreen(
                backAction = { currentScreen = Route.Welcome }
            )

            Route.Permissions -> PermissionsScreen(
                backAction = { currentScreen = Route.Welcome }
            )
        }
    }
}

expect fun getPlatformName(): String
