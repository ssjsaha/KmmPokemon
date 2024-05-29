import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import presentation.PokeListUiState
import presentation.PokeUiEvent

@Composable
fun PokeListComposable(uiState: StateFlow<PokeListUiState>, onEvent: (PokeUiEvent) -> Unit) {
    val composeState = uiState.collectAsState()

    if (composeState.value.loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (composeState.value.error.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = composeState.value.error)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(composeState.value.data.size) {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(20.dp),
                    horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top
                ) {
                    Text(text = composeState.value.data.get(it).name)
                }
            }
        }
    }
}