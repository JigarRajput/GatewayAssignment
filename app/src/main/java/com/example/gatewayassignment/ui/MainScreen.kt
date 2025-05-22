package com.example.gatewayassignment.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gatewayassignment.viewmodel.MainViewModel
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import org.json.JSONException
import org.json.JSONObject

@Composable
fun MainScreen(
    deepLinkData: Triple<String, Int, String>? = null,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val json = viewModel.apiResult

    var showDialog by remember { mutableStateOf(false) }

    val (status, code, jsonData) = deepLinkData ?: Triple("", 0, "")

    val prettyJson = try {
        val jsonObject = JSONObject(jsonData)
        jsonObject.toString(4) // Indent with 4 spaces (pretty print)
    } catch (e: JSONException) {
        "Invalid JSON: ${e.localizedMessage}"
    }

    LaunchedEffect(deepLinkData) {
        if (deepLinkData != null) {
            showDialog = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { openInAppBrowser(context) }) {
            Text("Open In App Browser")
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = { viewModel.callApi() }) {
            Text("Call API")
        }

        if (json.isNotEmpty() && viewModel.isDialogVisible) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissDialog() },
                title = { Text("API Result") },
                text = { Text(json) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val clipboard =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(ClipData.newPlainText("json", json))
                            Toast
                                .makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                .show()
                        },
                    ) {
                        Text("Copy")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            viewModel.dismissDialog()
                        },
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Deep Link Data") },
                text = {
                    Text("Status: $status\nCode: $code\nData:\n$prettyJson")
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}

fun openInAppBrowser(context: Context) {
    val url = "https://webcode.tools/generators/html/hyperlink"
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, url.toUri())
}

