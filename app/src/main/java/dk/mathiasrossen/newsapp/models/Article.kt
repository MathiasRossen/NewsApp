package dk.mathiasrossen.newsapp.models

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val source: Source,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val author: String?
): Serializable {
    val publishedAtFormatted: String
        get() {
            val localDateTime = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
            return localDateTime.format(formatter)
        }

    data class Source(val id: String?, val name: String): Serializable
}