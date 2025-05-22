package com.example.gatewayassignment

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import com.example.gatewayassignment.ui.MainScreen
import com.example.gatewayassignment.ui.theme.GatewayAssignmentTheme

class MainActivity : ComponentActivity() {

    private var deepLinkData: Triple<String, Int, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)

        setContent {
            GatewayAssignmentTheme {
                MainScreen(deepLinkData)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            handleIntent(it)
            setContent {  // Update the UI when new intent comes
                GatewayAssignmentTheme {
                    MainScreen(deepLinkData)
                }
            }
        }
    }

    private fun handleIntent(intent: Intent) {
        Log.d(TAG, "intent: $intent")
        intent.data?.let { uri: Uri ->
            val status = uri.getQueryParameter("status")
            val code = uri.getQueryParameter("code")?.toIntOrNull()
            val data = uri.getQueryParameter("data")
            if (status != null && code != null && data != null) {
                deepLinkData = Triple(status, code, data)
                Log.d(TAG, "deepLinkData: $deepLinkData")
            }
        }
    }
}
