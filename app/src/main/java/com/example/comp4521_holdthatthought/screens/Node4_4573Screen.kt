package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.ProgressDots
import com.example.comp4521_holdthatthought.ui.components.SecondaryButton
import com.example.comp4521_holdthatthought.ui.components.TagChip
import com.example.comp4521_holdthatthought.ui.theme.COMP4521HoldThatThoughtTheme
import com.example.comp4521_holdthatthought.ui.theme.Primary50
import com.example.comp4521_holdthatthought.ui.components.ImageFromDrawableName
import com.example.comp4521_holdthatthought.ui.components.Logo

@Composable
fun Node4_4573Screen(onPrimary: () -> Unit = {}, onSecondary: () -> Unit = {}, onSkip: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            TagChip(text = "Skip", modifier = Modifier.align(Alignment.CenterStart), onClick = onSkip)
            Logo(modifier = Modifier.align(Alignment.Center))
        }

        Spacer(Modifier.height(24.dp))
        ImageFromDrawableName(
            name = "read_with_intention",
            modifier = Modifier.fillMaxWidth().height(180.dp)
        )

        Spacer(Modifier.height(24.dp))
        Text(
            text = "Read With Intention",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Discover a calmer way to read. Save articles, focus fully, and reconnect with the joy of mindful reading.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))
        ProgressDots(total = 3, current = 2)

        Spacer(Modifier.weight(1f))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            PrimaryButton(text = "Continue", onClick = onPrimary)
            SecondaryButton(text = "Sign in", onClick = onSecondary)
        }

        Spacer(Modifier.height(24.dp))
    }
}

// No preview needed
