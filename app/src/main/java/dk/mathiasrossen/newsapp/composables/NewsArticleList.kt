package dk.mathiasrossen.newsapp.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dk.mathiasrossen.newsapp.models.ArticleResponse
import dk.mathiasrossen.newsapp.models.SampleData
import dk.mathiasrossen.newsapp.ui.theme.GapDefault
import dk.mathiasrossen.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsArticleList(articleResponse: ArticleResponse) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = GapDefault)
    ) {
        items(articleResponse.activeArticles) { article ->
            NewsArticle(article)
        }
    }
}

@Preview
@Composable
fun PreviewNewsArticleList() {
    NewsAppTheme {
        NewsArticleList(SampleData.articleResponseSample)
    }
}
