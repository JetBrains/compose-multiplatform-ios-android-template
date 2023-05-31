import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val f = remember { mutableStateOf<FontFamily?>(null) }

    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }
        var changedFont by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                greetingText = "Hello, ${getPlatformName()}"
                changedFont = !changedFont
            }) {
                Text(text = greetingText, fontFamily = if (changedFont) f.value ?: FontFamily.Default else FontFamily.Default)
            }
        }
    }

    LaunchedEffect(Unit) {

        val byteArray = try {
            resource("karla_regular.ttf").readBytes()
        } catch (e: Exception) {
            resource("karla_regular.otf").readBytes()
        }
        println("Creating a FontFamily from bytes")
        Font("karla_regular", byteArray, weight = FontWeight.Normal).let {
            println("Font = $it")
            f.value = FontFamily(listOf(it))
        }
    }
}

expect fun getPlatformName(): String