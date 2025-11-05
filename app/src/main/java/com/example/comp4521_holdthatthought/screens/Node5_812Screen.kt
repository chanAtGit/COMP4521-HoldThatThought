package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme
import com.example.comp4521_holdthatthought.ui.components.ImageFromDrawableName

data class MenuItem(val title: String, val time: String, val imageName: String)

private val mockMenuItems = listOf(
    MenuItem("The Da vinci Code", "Today 10:34", "cover_davinci"),
    MenuItem("Carrie Fisher", "Today 9:37", "cover_carrie"),
    MenuItem("The Good Sister", "Yesterday 18:45", "cover_good_sister"),
    MenuItem("The Waiting", "3 days ago", "cover_waiting"),
)

@Composable
fun Node5_812Screen(onItemClick: (MenuItem) -> Unit = {}) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(mockMenuItems) { item ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .clickable { onItemClick(item) }
            ) {
                androidx.compose.foundation.layout.Column(Modifier.padding(8.dp)) {
                    ImageFromDrawableName(
                        name = item.imageName,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .height(120.dp)
                            .fillMaxWidth()
                    )
                    Text(text = item.title, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(top = 8.dp))
                    Text(text = item.time, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                }
            }
        }
    }
}

// No preview needed
