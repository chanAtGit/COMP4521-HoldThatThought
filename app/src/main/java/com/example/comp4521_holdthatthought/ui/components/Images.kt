package com.example.comp4521_holdthatthought.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageFromDrawableName(
    name: String,
    modifier: Modifier = Modifier,
    clipRadius: Int = 16,
) {
    val context = LocalContext.current
    val resId = remember(name) {
        context.resources.getIdentifier(name, "drawable", context.packageName)
    }
    if (resId != 0) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = name,
            modifier = modifier.clip(RoundedCornerShape(clipRadius.dp))
        )
    } else {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(clipRadius.dp))
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f))
        )
    }
}

@Composable
fun AvatarFromDrawableName(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val resId = remember(name) { context.resources.getIdentifier(name, "drawable", context.packageName) }
    if (resId != 0) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = name,
            modifier = modifier.clip(CircleShape)
        )
    } else {
        Box(modifier = modifier.clip(CircleShape).background(Color(0xFFEDEDED)), contentAlignment = Alignment.Center) {}
    }
}

