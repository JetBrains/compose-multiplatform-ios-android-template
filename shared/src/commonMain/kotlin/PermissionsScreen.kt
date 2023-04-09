import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
internal fun PermissionsScreen(
    backAction: () -> Unit,
    viewModel: PermissionsViewModel = getViewModel(
        key = "permissions-screen",
        factory = viewModelFactory {
            PermissionsViewModel(PermissionsController())
        }
    )
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "moko-permissions") },
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
                Button(onClick = viewModel::onButtonClick) {
                    Text(text = "Click on me")
                }
            }
        }
    )
}

internal class PermissionsViewModel(
    val permissionsController: PermissionsController
) : ViewModel() {
    private val _state: MutableStateFlow<String> = MutableStateFlow("press button")
    val state: StateFlow<String> get() = _state

    fun onButtonClick() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.LOCATION)

                _state.value = "permission granted"
            } catch (exc: RequestCanceledException) {
                _state.value = "permission denied $exc"
            }
        }
    }
}
