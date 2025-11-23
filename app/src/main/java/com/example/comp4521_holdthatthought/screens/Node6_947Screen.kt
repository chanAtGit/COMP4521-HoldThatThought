package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme
import com.example.comp4521_holdthatthought.ui.theme.AppViewModel
import com.example.comp4521_holdthatthought.ui.theme.Primary50
import com.example.comp4521_holdthatthought.ui.components.ImageFromDrawableName
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton

@Composable
fun Node6_947Screen(
    viewModel: AppViewModel,
    onBack: () -> Unit = {},
    onAIQuestions: () -> Unit = {}
) {
    val summarizeState by viewModel.summarizeState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(Modifier.height(4.dp))
        // Actions block
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Actions", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text(text = "→ Save", style = MaterialTheme.typography.bodyMedium)
                Text(text = "→ Favourites", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(40.dp),
                    modifier = Modifier.clickable { onAIQuestions() }
                ) {
                    Text(
                        text = "AI Questions",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            ImageFromDrawableName(
                name = "img_detail_hero",
                modifier = Modifier
                    .weight(1f)
                    .height(130.dp)
            )
        }

        Spacer(Modifier.height(16.dp))
        Text(text = "Short Summary", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))

        // Summary State Handling
        when (summarizeState) {
            is AppViewModel.SummaryState.Idle -> {
                Text(
                    text = "Paste article text below to generate a summary",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(12.dp))
                PrimaryButton(
                    text = "Generate Summary",
                    onClick = {
                        // TODO: Fetch article content from URL or use saved content
                        // For MVP, use placeholder text
                        viewModel.summarizeArticle(
                            "Thailand visa information guide. Thailand offers multiple visa types " +
                            "for different purposes including tourist visas, retirement visas, and work visas. " +
                            "Tourist visas allow 60-day stays, retirement visas require 800,000 THB in Thai bank account, " +
                            "and work visas require employer sponsorship..."
                        )
                    }
                )
            }
            is AppViewModel.SummaryState.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is AppViewModel.SummaryState.Success -> {
                Text(
                    text = (summarizeState as AppViewModel.SummaryState.Success).summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }
            is AppViewModel.SummaryState.Error -> {
                Text(
                    text = "Error: ${(summarizeState as AppViewModel.SummaryState.Error).message}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(Modifier.height(12.dp))
                PrimaryButton(
                    text = "Retry",
                    onClick = {
                        viewModel.summarizeArticle(
                            "Thailand visa information guide. Thailand offers multiple visa types..."
                        )
                    }
                )
            }
        }
    }
}

// No preview needed
