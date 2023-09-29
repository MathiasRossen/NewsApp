package dk.mathiasrossen.newsapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.mathiasrossen.newsapp.models.ArticleResponse
import dk.mathiasrossen.newsapp.services.apiKey
import dk.mathiasrossen.newsapp.services.newsApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleListViewModel : ViewModel() {
    private val _articles = MutableStateFlow(ArticleResponse("", 0, listOf()))
    val articles = _articles.asStateFlow()
    private val _refreshing = mutableStateOf(false)
    val refreshing: State<Boolean> = _refreshing

    fun getArticles(country: String = "us") {
        viewModelScope.launch {
            _refreshing.value = true
            newsApiService.getArticles(apiKey, country).enqueue(object : Callback<ArticleResponse> {
                override fun onResponse(
                    call: Call<ArticleResponse>, response: Response<ArticleResponse>
                ) {
                    _refreshing.value = false
                    response.body()?.let { articleResponse ->
                        _articles.update { _ -> articleResponse }
                    }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    _refreshing.value = false
                    t.printStackTrace()
                }
            })
        }
    }
}
