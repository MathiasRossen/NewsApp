package dk.mathiasrossen.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import dk.mathiasrossen.newsapp.models.ArticleResponse
import dk.mathiasrossen.newsapp.services.apiKey
import dk.mathiasrossen.newsapp.services.newsApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleListViewModel : ViewModel() {
    private val _articles = MutableStateFlow(ArticleResponse("", 0, listOf()))
    val articles: StateFlow<ArticleResponse> = _articles.asStateFlow()

    fun getArticles(country: String = "us") {
        newsApiService.getArticles(apiKey, country).enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>, response: Response<ArticleResponse>
            ) {
                response.body()?.let { articleResponse ->
                    _articles.update { _ -> articleResponse }
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}