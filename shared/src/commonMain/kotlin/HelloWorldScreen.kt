import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.common.MR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.readTextAsState
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource as composePainterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun HelloWorldScreen(onButtonClick: () -> Unit) {
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

        var text: String by remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp),
            value = text,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onBackground
            ),
            onValueChange = { text = it }
        )

        val counter: Int = text.length
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 8.dp),
            text = stringResource(MR.plurals.chars_count, counter, counter),
            color = colorResource(MR.colors.textColor),
            fontFamily = fontFamilyResource(MR.fonts.cormorant.italic)
        )

        Button(onClick = { text = "Hello, ${getPlatformName()}" }) {
            Text(text = stringResource(MR.strings.hello_world))
        }

        Button(onClick = onButtonClick) {
            Text("Hello moko-mvvm World!")
        }

        val fileContent: String? by MR.files.some_file.readTextAsState()
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = fileContent.orEmpty(),
            color = MaterialTheme.colors.onBackground
        )

        val assetContent: String? by MR.assets.some_asset.readTextAsState()
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = assetContent.orEmpty(),
            color = MaterialTheme.colors.onBackground
        )

        Image(
            painter = composePainterResource("compose-multiplatform.xml"),
            contentDescription = null
        )
    }
}
