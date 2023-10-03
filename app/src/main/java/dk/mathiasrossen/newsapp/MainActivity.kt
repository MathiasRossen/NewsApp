package dk.mathiasrossen.newsapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dk.mathiasrossen.newsapp.composables.AppLayout
import dk.mathiasrossen.newsapp.composables.NewsArticleList
import dk.mathiasrossen.newsapp.composables.NotificationPermissionRequestDialog
import dk.mathiasrossen.newsapp.services.newsApiService
import dk.mathiasrossen.newsapp.viewmodels.ArticleListViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        val viewModel: ArticleListViewModel by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getArticles(newsApiService)
            }
        }
        askNotificationPermission {
            viewModel.changeNotificationPermissionDialogVisibility(true)
        }
        setContent {
            firebaseAnalytics.logEvent(
                FirebaseAnalytics.Event.SCREEN_VIEW,
                bundleOf(FirebaseAnalytics.Param.SCREEN_NAME to "Frontpage")
            )
            val articleResponse by viewModel.articles.collectAsState()
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            val openNotificationDialog by viewModel.openNotificationPermissionDialog.collectAsState()
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
                NewsArticleList(articleResponse = articleResponse)
            }

            when {
                openNotificationDialog -> {
                    NotificationPermissionRequestDialog(onDismissed = {
                        Toast.makeText(
                            this, "Hold your horses", Toast.LENGTH_SHORT
                        ).show()
                        viewModel.changeNotificationPermissionDialogVisibility(false)
                    }) {
                        Toast.makeText(this, "We ride at dawn!", Toast.LENGTH_SHORT).show()
                        viewModel.changeNotificationPermissionDialogVisibility(false)
                    }
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Can send notifications
        } else {
            Toast.makeText(
                this, "Permissions for notifications has been denied", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun askNotificationPermission(onAsk: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Can send notifications
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                onAsk()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}