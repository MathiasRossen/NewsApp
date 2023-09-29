package dk.mathiasrossen.newsapp.services

import dk.mathiasrossen.newsapp.models.ArticleResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    fun getArticles(
        @Header("Authorization") apiKey: String,
        @Query("country") country: String
    ): Call<ArticleResponse>
}

val apiKey = "9bf05c7546e9499f82ea4df0c24ce8d1"

val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://newsapi.org")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val newsApiService: NewsApiService = retrofit.create(NewsApiService::class.java)