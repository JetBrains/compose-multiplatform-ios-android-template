import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Composable
fun ViewModelScreen(
    backAction: () -> Unit,
    viewModel: SimpleViewModel = getViewModel(
        key = "simple-screen",
        factory = viewModelFactory { SimpleViewModel() }
    )
) = NavigationScreen(title = "moko-mvvm", backAction = backAction) { paddingValues ->
    val count: Int by viewModel.count.collectAsState()

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

class SimpleViewModel : ViewModel() {
    private val _count: MutableStateFlow<Int> = MutableStateFlow(0)
    val count: StateFlow<Int> get() = _count

    init {
        println("view model $this created!")
    }

    fun onCountClick() {
        _count.update { it + 1 }
    }

    override fun onCleared() {
        super.onCleared()

        println("view model $this cleared!")
    }
}
