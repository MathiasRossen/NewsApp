package dk.mathiasrossen.newsapp.viewmodels

import androidx.lifecycle.ViewModel
import dk.mathiasrossen.newsapp.models.ArticleResponse
import dk.mathiasrossen.newsapp.services.NewsApiService
import dk.mathiasrossen.newsapp.services.apiKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleListViewModel : ViewModel() {
    private val _articles = MutableStateFlow(ArticleResponse("", 0, listOf()))
    val articles: StateFlow<ArticleResponse> = _articles.asStateFlow()
    private val _openNotifcationPermissionDialog = MutableStateFlow(false)
    val openNotificationPermissionDialog: StateFlow<Boolean> = _openNotifcationPermissionDialog.asStateFlow()

    // Getting the service through params for testing purposes
    fun getArticles(newsApiService: NewsApiService, country: String = "us") {
        newsApiService.getArticles(apiKey, country).enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>, response: Response<ArticleResponse>
            ) {
                response.body()?.let { articleResponse ->
                    _articles.value = articleResponse
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun changeNotificationPermissionDialogVisibility(open: Boolean) {
        _openNotifcationPermissionDialog.value = open
    }
}