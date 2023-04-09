import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.common.MR
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun WelcomeScreen(route: (Route) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(MR.images.moko_logo),
            contentDescription = null
        )

        Button(onClick = { route(Route.Resources) }) {
            Text(text = "moko-resources")
        }

        Button(onClick = { route(Route.ViewModel) }) {
            Text(text = "moko-mvvm")
        }

        Button(onClick = { route(Route.Permissions) }) {
            Text(text = "moko-permissions")
        }
    }
}
