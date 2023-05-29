package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.model.Fact
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonAndroidExerciseTheme
import jp.speakbuddy.edisonandroidexercise.viewmodel.FactViewModel
import kotlinx.coroutines.launch

@Composable
fun FactScreen(
    viewModel: FactViewModel
) {

    val factState by viewModel.factState.collectAsState(initial = Fact("", 0))

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

        FactTextView(fact = factState!!.fact)
        CheckAndAddLengthTextView(factLength = factState!!.length)
        val onClick: () -> Unit = {
            coroutineScope.launch {
                viewModel.updateFact()
                viewModel.getFactFromLocal()
            }
        }
        ButtonView(onClick)
    }
}

@Composable
private fun FactTextView(fact: String) {
    Text(
        text = "Fact",
        style = MaterialTheme.typography.titleLarge
    )

    if (fact.contains("cat", ignoreCase = true)) {
        Text(
            text = "Multiple cats!",
            style = MaterialTheme.typography.titleMedium,
        )
    }

    Text(
        text = fact,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun CheckAndAddLengthTextView(factLength: Int) {
    if (factLength >= 100) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Text(
                text = "Length: $factLength",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun ButtonView(onClick: () -> Unit) {
    Button(onClick) {
        Text(text = "Update fact")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
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
            FactTextView(fact = "I have many cats at my home in India")
            CheckAndAddLengthTextView(factLength = 101)
            ButtonView {

            }
        }
    }
}
