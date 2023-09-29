package dk.mathiasrossen.newsapp.models

data class ArticleResponse(val status: String, val totalResults: Int, val articles: List<Article>)