package com.example.comp4521_holdthatthought.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.comp4521_holdthatthought.ui.components.PrimaryButton
import com.example.comp4521_holdthatthought.ui.components.SecondaryButton
import com.example.comp4521_holdthatthought.ui.components.TagChip
import com.example.comp4521_holdthatthought.ui.theme.AppViewModel


@Composable
fun AIQuestionScreen(
    viewModel: AppViewModel,
    onSubmit: () -> Unit,
    onCancel: () -> Unit
) {
    val questionsState by viewModel.questionsState.collectAsStateWithLifecycle()
    val currentQuestion by viewModel.currentQuestion.collectAsStateWithLifecycle()
    val (userAnswer, setUserAnswer) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TagChip(text = "Skip", onClick = onCancel)
        Spacer(Modifier.height(16.dp))

        when (questionsState) {
            is AppViewModel.QuestionsState.Idle -> {
                Text(
                    text = "Generate comprehension questions based on the article",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(16.dp))
                PrimaryButton(
                    text = "Generate Questions",
                    onClick = {
                        // Use placeholder article text for MVP
                        viewModel.generateQuestions(
                            "Thailand visa information guide. Thailand offers multiple visa types " +
                            "for different purposes including tourist visas, retirement visas, and work visas. " +
                            "Tourist visas allow 60-day stays and can be extended for 30 more days. " +
                            "Retirement visas require applicants to have 800,000 THB in a Thai bank account..."
                        )
                    }
                )
            }
            is AppViewModel.QuestionsState.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is AppViewModel.QuestionsState.Success -> {
                val questions = (questionsState as AppViewModel.QuestionsState.Success).questions
                if (currentQuestion == null && questions.isNotEmpty()) {
                    viewModel.setCurrentQuestion(questions[0])
                }

                currentQuestion?.let { question ->
                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(16.dp))
                    Text("Your Answer", style = MaterialTheme.typography.titleMedium)
                    OutlinedTextField(
                        value = userAnswer,
                        onValueChange = setUserAnswer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        PrimaryButton(
                            text = "Submit",
                            onClick = {
                                viewModel.checkAnswer(
                                    question = question.question,
                                    userAnswer = userAnswer,
                                    aiAnswer = question.answer
                                )
                                onSubmit()
                            }
                        )
                        SecondaryButton(text = "Cancel", onClick = onCancel)
                    }
                }
            }
            is AppViewModel.QuestionsState.Error -> {
                Text(
                    text = "Error: ${(questionsState as AppViewModel.QuestionsState.Error).message}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(Modifier.height(12.dp))
                PrimaryButton(
                    text = "Retry",
                    onClick = {
                        viewModel.generateQuestions(
                            "Thailand visa information guide..."
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun AIResultScreen(
    viewModel: AppViewModel,
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    val checkAnswerState by viewModel.checkAnswerState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when (checkAnswerState) {
            is AppViewModel.CheckAnswerState.Idle -> {
                Text(
                    text = "No question to evaluate",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            is AppViewModel.CheckAnswerState.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is AppViewModel.CheckAnswerState.Success -> {
                val result = (checkAnswerState as AppViewModel.CheckAnswerState.Success).result
                val isCorrect = result.result == "correct"

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
                            .padding(horizontal = 100.dp)
                            .background(
                                color = if (isCorrect) Color(0xFF2ECC71) else Color(0xFFE74C3C),
                                shape = RoundedCornerShape(40.dp)
                            )
                    )
                    Text(
                        text = if (isCorrect) "Correct" else "Incorrect",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                }
                Spacer(Modifier.height(16.dp))
                Text("Feedback", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = result.comment,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    enabled = false
                )
                Spacer(Modifier.height(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    PrimaryButton(text = "Next", onClick = onNext)
                    SecondaryButton(text = "Cancel", onClick = onCancel)
                }
            }
            is AppViewModel.CheckAnswerState.Error -> {
                Text(
                    text = "Error: ${(checkAnswerState as AppViewModel.CheckAnswerState.Error).message}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(Modifier.height(12.dp))
                PrimaryButton(
                    text = "Back",
                    onClick = onCancel
                )
            }
        }
    }
}

// No previews needed in runtime build
