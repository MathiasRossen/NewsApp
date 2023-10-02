package dk.mathiasrossen.newsapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class NewsAppFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("FCM TOKEN", token)
    }
}