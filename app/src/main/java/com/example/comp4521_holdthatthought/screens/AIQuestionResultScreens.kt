package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.SecondaryButton
import com.example.comp4521_holdthatthought.ui.components.TagChip
 

@Composable
fun AIQuestionScreen(onSubmit: () -> Unit, onCancel: () -> Unit) {
    val (answer, setAnswer) = remember { mutableStateOf("John said I know the answer...") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TagChip(text = "Skip", onClick = onCancel)
        Spacer(Modifier.height(16.dp))
        Text(
            text = "When did John enter the garden according to Molly Click?",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))
        Text("Answer", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = answer,
            onValueChange = setAnswer,
            modifier = Modifier.fillMaxWidth().height(140.dp)
        )
        Spacer(Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton(text = "Submit", onClick = onSubmit)
            SecondaryButton(text = "Cancel", onClick = onCancel)
        }
    }
}

@Composable
fun AIResultScreen(onNext: () -> Unit, onCancel: () -> Unit) {
    val (comment, setComment) = remember { mutableStateOf("John said I know the answer...") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(top = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .padding(horizontal = 120.dp)
                    .background(color = Color(0xFF2ECC71), shape = RoundedCornerShape(40.dp))
            )
            Text(text = "Correct", style = MaterialTheme.typography.labelMedium, color = Color.White)
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "When did John enter the garden according to Molly Click?",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))
        Text("AI Comment", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = comment,
            onValueChange = setComment,
            modifier = Modifier.fillMaxWidth().height(140.dp)
        )
        Spacer(Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton(text = "Next", onClick = onNext)
            SecondaryButton(text = "Cancel", onClick = onCancel)
        }
    }
}

// No previews needed in runtime build
