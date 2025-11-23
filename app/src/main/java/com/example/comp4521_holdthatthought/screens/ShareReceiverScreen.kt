package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.SecondaryButton
import com.example.comp4521_holdthatthought.ui.theme.AppViewModel
import com.example.comp4521_holdthatthought.ui.theme.Article
import com.example.comp4521_holdthatthought.ui.theme.Status

@Composable
fun ShareReceiverScreen(
    viewModel: AppViewModel,
    sharedText: String,
    onSave: (title: String, url: String, notes: String) -> Unit,
    onCancel: () -> Unit
) {
    // Extract URL from shared text if present
    val urlPattern = remember { Regex("https?://[^\\s]+") }
    val extractedUrl = remember(sharedText) {
        urlPattern.find(sharedText)?.value ?: ""
    }
    val extractedTitle = remember(sharedText) {
        // If there's text before the URL, use it as title; otherwise use URL domain
        val urlMatch = urlPattern.find(sharedText)
        if (urlMatch != null && urlMatch.range.first > 0) {
            sharedText.substring(0, urlMatch.range.first).trim()
        } else if (extractedUrl.isNotEmpty()) {
            try {
                java.net.URI(extractedUrl).host?.removePrefix("www.") ?: "Shared Article"
            } catch (e: Exception) {
                "Shared Article"
            }
        } else {
            "Shared Article"
        }
    }

    var title by remember { mutableStateOf(extractedTitle) }
    var url by remember { mutableStateOf(extractedUrl) }
    var notes by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top bar with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onCancel) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Cancel"
                )
            }
            Text(
                text = "Save Article",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Show what was shared
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Shared content:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = sharedText,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title field
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // URL field
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("URL") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Notes field
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes (optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Action buttons
        if (isSaving) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            PrimaryButton(
                text = "Save Article",
                onClick = {
                    isSaving = true
                    // Create article and save to database
                    val article = Article(
                        id = 0, // AutoGenerate
                        userId = 1, // Default user (TODO: use logged-in user ID)
                        title = title,
                        url = url,
                        content = "", // Will be fetched later
                        addedDate = System.currentTimeMillis(),
                        readStatus = Status.READING,
                        progress = 0f,
                        readDate = 0L,
                        tagId = "" // Will be assigned later
                    )
                    viewModel.insertArticle(article)
                    onSave(title, url, notes)
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            SecondaryButton(
                text = "Cancel",
                onClick = onCancel
            )
        }
    }
}
