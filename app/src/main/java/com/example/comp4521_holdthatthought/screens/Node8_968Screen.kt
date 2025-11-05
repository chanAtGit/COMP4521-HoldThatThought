package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme

data class SavedItem(val title: String, val date: String)

private val mockSaved = listOf(
    SavedItem("Mindful Reading Habits", "Today 10:22"),
    SavedItem("How to Focus", "Yesterday 19:01"),
    SavedItem("Context Overload", "Mon 08:32"),
)

@Composable
fun Node8_968Screen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item { Text("Saved", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp)) }
        items(mockSaved) { item ->
            Card(modifier = Modifier.padding(vertical = 6.dp)) {
                androidx.compose.foundation.layout.Column(Modifier.padding(16.dp)) {
                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                    Text(item.date, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                }
            }
        }
    }
}

// No preview needed
