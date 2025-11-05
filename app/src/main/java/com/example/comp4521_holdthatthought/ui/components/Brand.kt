package com.example.comp4521_holdthatthought.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Logo(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    // Prefer VectorDrawable/PNG in drawable if available
    // Prefer the vector/bitmap named logo_hold_that_thought if present
    val drawableId = remember { context.resources.getIdentifier("logo_hold_that_thought", "drawable", context.packageName) }
    if (drawableId != 0) {
        Image(painter = painterResource(id = drawableId), contentDescription = "HoldThatThought", modifier = modifier.height(28.dp))
        return
    }

    // Last resort: text logo
    Text("HoldThatThought", style = MaterialTheme.typography.titleMedium, modifier = modifier)
}
