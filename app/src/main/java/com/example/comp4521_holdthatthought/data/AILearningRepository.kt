package com.example.comp4521_holdthatthought.data

class AILearningRepository(private val service: AILearningApiService) {

    suspend fun summarizeArticle(text: String): Result<String> = runCatching {
        val request = SummarizeRequest(text = text)
        val response = service.summarizeArticle(request)
        if (response.success) {
            response.summary
        } else {
            throw Exception("API returned success=false")
        }
    }

    suspend fun generateQuestions(text: String): Result<List<QuestionAnswer>> = runCatching {
        val request = GenerateQuestionsRequest(text = text)
        val response = service.generateQuestions(request)
        if (response.success) {
            response.questions
        } else {
            throw Exception("API returned success=false")
        }
    }

    suspend fun checkAnswer(
        question: String,
        userAnswer: String,
        aiAnswer: String
    ): Result<CheckAnswerResponse> = runCatching {
        val request = CheckAnswerRequest(
            question = question,
            user_answer = userAnswer,
            ai_answer = aiAnswer
        )
        val response = service.checkAnswer(request)
        if (response.success) {
            response
        } else {
            throw Exception("API returned success=false")
        }
    }
}
