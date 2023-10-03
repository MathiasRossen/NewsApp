package dk.mathiasrossen.newsapp

import dk.mathiasrossen.newsapp.models.Article
import dk.mathiasrossen.newsapp.models.ArticleResponse
import dk.mathiasrossen.newsapp.services.NewsApiService
import dk.mathiasrossen.newsapp.services.apiKey
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ArticleResponseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var newsApiService: NewsApiService

    @MockK
    lateinit var articleResponseCall: Call<ArticleResponse>

    private val article = Article(
        Article.Source("horse", "Horse"),
        "Horses are great",
        "We like this creature so much, it's unbelievable",
        "https://horse-enthusiasts.com/",
        null,
        "2023-09-29T04:50:53Z",
        "BoJack Horseman"
    )

    private val articleResponseSample = ArticleResponse(
        "ok",
        5,
        listOf(
            article,
            article.copy(source = Article.Source(null, "[Removed]")),
            article.copy(source = Article.Source("cats", "[Removed]")),
            article.copy(source = Article.Source(null, "[Removed]")),
            article.copy(source = Article.Source("fish", "Fish"))
        )
    )

    @Before
    fun setup() {
        every { articleResponseCall.execute() } returns Response.success(articleResponseSample)
        every { newsApiService.getArticles(apiKey, "us") } returns articleResponseCall
    }

    @Test
    fun filteringActiveArticlesCorrect() {
        val articleResponse = newsApiService.getArticles(apiKey, "us").execute().body()
        assertNotNull(articleResponse)
        if (articleResponse != null) {
            assertEquals(2, articleResponse.activeArticles.size)
            assertEquals("horse", articleResponse.activeArticles.first().source.id)
            assertEquals("fish", articleResponse.activeArticles.last().source.id)
            assert(!articleResponse.activeArticles.any { article -> article.source.name == "[Removed]" })
        }
    }
}