package com.example.comp4521_holdthatthought.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext

@Composable
fun RemoteImage(url: String?, contentDescription: String?, modifier: Modifier = Modifier) {
    if (url.isNullOrBlank()) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
        )
    }
}
