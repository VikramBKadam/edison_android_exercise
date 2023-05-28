package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.model.Fact
import jp.speakbuddy.edisonandroidexercise.viewmodel.FactViewModel
import kotlinx.coroutines.launch

@Composable
fun FactScreen(
    viewModel: FactViewModel
) {

    val factState = remember { mutableStateOf(Fact("", 0)) }

    LaunchedEffect(Unit) {
        viewModel.factState.collect { fact ->
            factState.value = fact ?: Fact("", 0)
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        Text(
            text = "Fact",
            style = MaterialTheme.typography.titleLarge
        )

        if (factState.value.fact.contains("cat", ignoreCase = true)) {
            Text(
                text = "Multiple cats!",
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Text(
            text = factState.value.fact,
            style = MaterialTheme.typography.bodyLarge
        )

        if (factState.value.length >= 100) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = "Length: " + factState.value.length,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }
        }

        val onClick: () -> Unit = {
            coroutineScope.launch {
                viewModel.updateFact()
                viewModel.getFactFromLocal()
            }
        }

        Button(onClick) {
            Text(text = "Update fact")
        }
    }
}
