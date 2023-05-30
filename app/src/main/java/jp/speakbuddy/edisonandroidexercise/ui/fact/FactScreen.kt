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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_BUTTON_UPDATE_FACT
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_FACT
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_FACT_TITLE
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_LENGTH
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_MULTIPLE_CATS
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_UPDATE_FACT
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_LENGTH_MORE_THAN_100
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_WITH_CAT
import jp.speakbuddy.edisonandroidexercise.R
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
fun FactTextView(fact: String) {
    Text(
        text = stringResource(id = R.string.fact),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.testTag(TAG_TEXT_FACT_TITLE)
    )

    if (fact.contains(stringResource(id = R.string.cats), ignoreCase = true)) {
        Text(
            text = stringResource(id = R.string.multiple_cats),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.testTag(TAG_TEXT_MULTIPLE_CATS)
        )
    }

    Text(
        text = fact,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.testTag(TAG_TEXT_FACT)
    )
}

@Composable
 fun CheckAndAddLengthTextView(factLength: Int) {
    if (factLength >= 100) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.length, factLength.toString()),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .testTag(TAG_TEXT_LENGTH)
            )
        }
    }
}

@Composable
fun ButtonView(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.testTag(TAG_BUTTON_UPDATE_FACT)) {
        Text(
            stringResource(id = R.string.update_fact),
            modifier = Modifier.testTag(TAG_TEXT_UPDATE_FACT)
        )
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
            FactTextView(fact = TEST_FACT_WITH_CAT)
            CheckAndAddLengthTextView(factLength = TEST_FACT_LENGTH_MORE_THAN_100)
            ButtonView {

            }
        }
    }
}
