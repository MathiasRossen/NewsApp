package dk.mathiasrossen.newsapp

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dk.mathiasrossen.newsapp.composables.NewsArticleList
import dk.mathiasrossen.newsapp.models.SampleData
import dk.mathiasrossen.newsapp.ui.theme.NewsAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsArticleListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NewsAppTheme {
                NewsArticleList(articleResponse = SampleData.articleResponseSample)
            }
        }
    }

    @Test
    fun articlesAreClickable() {
        composeTestRule.onNodeWithText("Meta", substring = true).assertHasClickAction()
        composeTestRule.onNodeWithText("Hyundai", substring = true).assertHasClickAction()
    }
}
