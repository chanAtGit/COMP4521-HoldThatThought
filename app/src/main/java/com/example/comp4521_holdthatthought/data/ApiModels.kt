package com.example.comp4521_holdthatthought.data

import kotlinx.serialization.Serializable

// ============ Summarize Endpoint ============
@Serializable
data class SummarizeRequest(
    val text: String
)

@Serializable
data class SummarizeResponse(
    val success: Boolean,
    val summary: String
)

// ============ Generate Questions Endpoint ============
@Serializable
data class GenerateQuestionsRequest(
    val text: String
)

@Serializable
data class QuestionAnswer(
    val question: String,
    val answer: String
)

@Serializable
data class GenerateQuestionsResponse(
    val success: Boolean,
    val questions: List<QuestionAnswer>
)

// ============ Check Answer Endpoint ============
@Serializable
data class CheckAnswerRequest(
    val question: String,
    val user_answer: String,
    val ai_answer: String
)

@Serializable
data class CheckAnswerResponse(
    val success: Boolean,
    val result: String, // "correct" or "incorrect"
    val comment: String
)
