package dk.mathiasrossen.newsapp.unittests

import dk.mathiasrossen.newsapp.models.Article
import dk.mathiasrossen.newsapp.models.ArticleResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleResponseTest {
    private val article = Article(
        Article.Source("horse", "Horse"),
        "Horses are great",
        "We like this creature so much, it's unbelievable",
        "https://horselovers.com/",
        null,
        "2023-09-29T04:50:53Z",
        "BoJack Horseman"
    )

    private val articleResponse = ArticleResponse(
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

    @Test
    fun filteringActiveArticlesCorrect() {
        assertEquals(2, articleResponse.activeArticles.size)
        assertEquals("horse", articleResponse.activeArticles.first().source.id)
        assertEquals("fish", articleResponse.activeArticles.last().source.id)
        assert(!articleResponse.activeArticles.any { article -> article.source.name == "[Removed]" })
    }
}