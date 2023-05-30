package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_BUTTON_UPDATE_FACT
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_FACT
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_FACT_TITLE
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_LENGTH
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TAG_TEXT_MULTIPLE_CATS
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_LENGTH_LESS_THAN_100
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_LENGTH_MORE_THAN_100
import jp.speakbuddy.edisonandroidexercise.AppConstants.ConstantStrings.TEST_FACT_WITH_CAT
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.view.MainActivity
import org.junit.Rule
import org.junit.Test


class FactScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyWhetherTitleAndFactISShown() {
        composeTestRule.activity.setContent { FactTextView(fact = TEST_FACT_WITH_CAT) }
        composeTestRule.onNodeWithTag(TAG_TEXT_FACT_TITLE).assertTextContains(
            composeTestRule.activity.getString(
                R.string.fact
            )
        ).assertExists()
        composeTestRule.onNodeWithTag(TAG_TEXT_FACT).assertTextContains(
            TEST_FACT_WITH_CAT
        ).assertExists()
    }

    @Test
    fun verifyWhetherMultipleCatsTextIsShown() {
        composeTestRule.activity.setContent { FactTextView(fact = TEST_FACT_WITH_CAT) }
        composeTestRule.onNodeWithTag(
            TAG_TEXT_MULTIPLE_CATS
        ).assertTextContains(
            composeTestRule.activity.getString(
                R.string.multiple_cats
            )
        ).assertExists()

    }


    @Test
    fun checkAndAddLengthTextViewForLengthMoreThan100() {
        composeTestRule.activity.setContent { CheckAndAddLengthTextView(factLength = TEST_FACT_LENGTH_MORE_THAN_100) }
        composeTestRule.onNodeWithTag(TAG_TEXT_LENGTH).assertTextContains(
            composeTestRule.activity.getString(
                R.string.length,
                TEST_FACT_LENGTH_MORE_THAN_100
            )
        ).assertExists()

    }

    @Test
    fun checkAndAddLengthTextViewForLengthLessThan100() {
        composeTestRule.activity.setContent { CheckAndAddLengthTextView(factLength = TEST_FACT_LENGTH_LESS_THAN_100) }
        composeTestRule.onNodeWithTag(TAG_TEXT_LENGTH)
            .assertDoesNotExist()

    }

    @Test
    fun checkButtonView() {
        var buttonClicked = false
        composeTestRule.activity.setContent {
            ButtonView {
                buttonClicked = true
            }
        }
        composeTestRule.onNodeWithTag(TAG_BUTTON_UPDATE_FACT).performClick()
        assert(buttonClicked)

    }
}
