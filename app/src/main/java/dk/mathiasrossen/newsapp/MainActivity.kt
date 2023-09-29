package dk.mathiasrossen.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dk.mathiasrossen.newsapp.composables.AppLayout
import dk.mathiasrossen.newsapp.composables.ArticleList
import dk.mathiasrossen.newsapp.viewmodels.ArticleListViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ArticleListViewModel by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getArticles()
            }
        }
        setContent {
            val articleResponse by viewModel.articles.collectAsState()
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            AppLayout(scrollBehavior = scrollBehavior, topBar = {
                TopAppBar(
                    title = { Text(text = "NewsApp") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    scrollBehavior = scrollBehavior
                )
            }) {
                ArticleList(articleResponse = articleResponse)
            }
        }
    }
}