package dk.mathiasrossen.newsapp.composables

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dk.mathiasrossen.newsapp.ARTICLE_KEY
import dk.mathiasrossen.newsapp.DetailsActivity
import dk.mathiasrossen.newsapp.models.Article
import dk.mathiasrossen.newsapp.models.SampleData
import dk.mathiasrossen.newsapp.ui.theme.GapDefault
import dk.mathiasrossen.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsArticle(article: Article) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .wrapContentHeight()
        .padding(top = GapDefault)
        .fillMaxWidth()
        .height(150.dp)
        .clickable {
            Intent(context, DetailsActivity::class.java).let { intent ->
                intent.putExtra(ARTICLE_KEY, article)
                context.startActivity(intent)
            }
        }) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                if (article.urlToImage != null) {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = null,
                        modifier = Modifier
                            .width(120.dp)
                            .height(100.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Text(
                    text = article.title,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }
            Text(
                text = article.publishedAtFormatted,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun NewArticlePreview() {
    NewsAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            NewsArticle(SampleData.articlesSample.first())
        }
    }
}