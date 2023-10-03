package dk.mathiasrossen.newsapp

import dk.mathiasrossen.newsapp.models.Article
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleTest {
    private val article = Article(
        Article.Source("horse", "Horse"),
        "Horses are great",
        "We like this creature so much, it's unbelievable",
        "https://horselovers.com/",
        null,
        "2023-09-29T04:50:53Z",
        "BoJack Horseman"
    )

    @Test
    fun formattedDateIsCorrect() {
        val article2 = article.copy(publishedAt = "2023-10-12T14:30:00Z")
        val article3 = article.copy(publishedAt = "2023-11-24T23:51:53Z")
        assertEquals("29-09-2023 04:50", article.publishedAtFormatted)
        assertEquals("12-10-2023 14:30", article2.publishedAtFormatted)
        assertEquals("24-11-2023 23:51", article3.publishedAtFormatted)
    }
}