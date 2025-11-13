package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.SecondaryButton
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme
import com.example.comp4521_holdthatthought.ui.theme.Primary50

@Composable
fun Node8_1053Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Article", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Surface(color = Primary50, modifier = Modifier.fillMaxWidth().height(160.dp)) {}
        Spacer(Modifier.height(16.dp))
        Text(
            text = "A helpful summary goes here, highlighting the key points of the article.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton(text = "Save", onClick = { })
            SecondaryButton(text = "Add to Favorites", onClick = { })
        }
    }
}

// No preview needed
