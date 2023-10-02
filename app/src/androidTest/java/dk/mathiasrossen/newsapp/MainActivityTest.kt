package dk.mathiasrossen.newsapp

import android.Manifest
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.rule.GrantPermissionRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.POST_NOTIFICATIONS
    )

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun clickOnArticle() {
        composeTestRule.onRoot().printToLog("HESTMANTICS")
    }
}
