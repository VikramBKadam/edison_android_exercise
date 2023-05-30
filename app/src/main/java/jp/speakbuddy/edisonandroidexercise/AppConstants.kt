package jp.speakbuddy.edisonandroidexercise

class AppConstants {

    object ConstantStrings {
        // For Fact Datastore
        const val FACT_DATA_STORE = "fact_data_store"
        const val FACT_KEY_STRING = "fact_key"

        // For UI tags
        const val TAG_TEXT_FACT_TITLE = "tag_text_fact_title"
        const val TAG_TEXT_MULTIPLE_CATS = "tag_text_multiple_cats"
        const val TAG_TEXT_FACT = "tag_text_fact"
        const val TAG_TEXT_LENGTH = "tag_text_length"
        const val TAG_BUTTON_UPDATE_FACT = "tag_button_update_fact"
        const val TAG_TEXT_UPDATE_FACT = "tag_text_update_fact"
        const val TAG_TEXT_FACTS_APP = "tag_text_facts_app"

        // Testing fact strings and length
        const val TEST_FACT_WITH_CAT = "I have many cats at my home in India"
        const val TEST_FACT_WITHOUT_CAT = "I have many dogs at my home in India"
        const val TEST_FACT_LENGTH_MORE_THAN_100 = 101
        const val TEST_FACT_LENGTH_LESS_THAN_100 = 99

    }
}
