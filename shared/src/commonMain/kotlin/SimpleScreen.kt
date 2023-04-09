import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
internal fun SimpleScreen(
    backAction: () -> Unit,
    viewModel: SimpleViewModel = getViewModel(
        key = "simple-screen",
        factory = viewModelFactory { SimpleViewModel() }
    )
) {
    val count: Int by viewModel.count.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "moko-mvvm") },
                navigationIcon = {
                    IconButton(onClick = backAction) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = count.toString())

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = viewModel::onCountClick) {
                    Text(text = "Click on me")
                }
            }
        }
    )
}
