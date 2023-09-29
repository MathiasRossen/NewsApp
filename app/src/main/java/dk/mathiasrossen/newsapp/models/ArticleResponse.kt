package dk.mathiasrossen.newsapp.models

data class ArticleResponse(val status: String, val totalResults: Int, val articles: List<Article>) {
    val activeArticles
        get() = articles.filter { article -> article.source.id != null }
}