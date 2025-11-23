package com.example.comp4521_holdthatthought.data

import retrofit2.http.Body
import retrofit2.http.POST

interface AILearningApiService {

    @POST("/summarize")
    suspend fun summarizeArticle(@Body request: SummarizeRequest): SummarizeResponse

    @POST("/generate_questions")
    suspend fun generateQuestions(@Body request: GenerateQuestionsRequest): GenerateQuestionsResponse

    @POST("/check")
    suspend fun checkAnswer(@Body request: CheckAnswerRequest): CheckAnswerResponse
}
