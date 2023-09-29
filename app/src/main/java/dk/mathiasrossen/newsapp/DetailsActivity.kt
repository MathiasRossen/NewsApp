package dk.mathiasrossen.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dk.mathiasrossen.newsapp.composables.AppLayout
import dk.mathiasrossen.newsapp.models.Article
import dk.mathiasrossen.newsapp.models.SampleData
import dk.mathiasrossen.newsapp.ui.theme.GapDefault

const val ARTICLE_KEY = "article"

class DetailsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                rememberTopAppBarState()
            )
            @Suppress("DEPRECATION") val article =
                intent.extras?.getSerializable(ARTICLE_KEY) as Article
            AppLayout(scrollBehavior = scrollBehavior, topBar = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = { finish() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Tilbage"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color.Transparent,
                            navigationIconContentColor = Color.White
                        )
                    )
                }
            }) {
                Surface(modifier = Modifier.padding(all = GapDefault)) {
                    ArticleDetail(article = article)
                }
            }
        }
    }
}

@Composable
fun ArticleDetail(article: Article) {
    Column(verticalArrangement = Arrangement.spacedBy(GapDefault)) {
        Text(
            text = article.title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )
        article.description?.let { description ->
            Text(
                text = description,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            article.author?.let { author ->
                Text(
                    text = author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = article.publishedAtFormatted,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "View full article")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleDetail() {
    ArticleDetail(article = SampleData.articlesSample.first())
}
