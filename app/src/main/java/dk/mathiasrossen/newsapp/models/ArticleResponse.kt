package dk.mathiasrossen.newsapp.models

data class ArticleResponse(val status: String, val totalResults: Int, val articles: List<Article>) {
    val activeArticles
        // Wanted to check article.source.id == null, but it seems it can be null and still be active
        get() = articles.filter { article -> article.source.name != "[Removed]" }
}