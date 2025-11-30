package com.example.comp4521_holdthatthought.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.comp4521_holdthatthought.data.AILearningRepository
import com.example.comp4521_holdthatthought.data.CheckAnswerResponse
import com.example.comp4521_holdthatthought.data.QuestionAnswer
import com.example.comp4521_holdthatthought.data.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * A single ViewModel that holds all repository instances.
 * It is created with an [Application] so it can obtain the [AppDatabase] instance.
 */
class AppViewModel(application: Application) : AndroidViewModel(application) {

    // -------------------------------------------------------------------------
    // Database & Repositories
    // -------------------------------------------------------------------------
    private val db: AppDatabase = AppDatabase.getInstance(application)

    val userRepo = UserRepo(db.userDao())
    val articleRepo = ArticleRepo(db.articleDao())
    val promptRepo = PromptRepo(db.promptDao())
    val streakRepo = StreakRepo(db.streakDao())

    private val aiLearningRepo = AILearningRepository(RetrofitClient.aiLearningService)

    // -------------------------------------------------------------------------
    // Initialization
    // -------------------------------------------------------------------------
    init {
        // Ensure a default user with id=1 exists for MVP
        // This user is used for all articles in the MVP
        viewModelScope.launch {
            ensureDefaultUserExists()
        }
    }

    private suspend fun ensureDefaultUserExists() {
        val existingUser = userRepo.getById(1)
        if (existingUser == null) {
            val defaultUser = User(
                id = 1,
                name = "Default User",
                email = "user@holdthatthought.app",
                preference = "",
                totalPoints = 0,
                lastSyncDate = System.currentTimeMillis()
            )
            userRepo.insert(defaultUser)
        }
    }

    // -------------------------------------------------------------------------
    // Exposed Flows (read‑only)
    // -------------------------------------------------------------------------
    val allUsers: Flow<List<User>> = userRepo.allItems
    val allArticles: Flow<List<Article>> = articleRepo.allItems
    val allPrompts: Flow<List<Prompt>> = promptRepo.allItems
    val allStreaks: Flow<List<Streak>> = streakRepo.allItems

    // -------------------------------------------------------------------------
    // API State Management
    // -------------------------------------------------------------------------
    private val _summarizeState = MutableStateFlow<SummaryState>(SummaryState.Idle)
    val summarizeState: StateFlow<SummaryState> = _summarizeState.asStateFlow()

    private val _questionsState = MutableStateFlow<QuestionsState>(QuestionsState.Idle)
    val questionsState: StateFlow<QuestionsState> = _questionsState.asStateFlow()

    private val _checkAnswerState = MutableStateFlow<CheckAnswerState>(CheckAnswerState.Idle)
    val checkAnswerState: StateFlow<CheckAnswerState> = _checkAnswerState.asStateFlow()

    private val _currentQuestion = MutableStateFlow<QuestionAnswer?>(null)
    val currentQuestion: StateFlow<QuestionAnswer?> = _currentQuestion.asStateFlow()

    private val _currentArticle = MutableStateFlow<Article?>(null)
    val currentArticle: StateFlow<Article?> = _currentArticle.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow<Int>(-1)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    // -------------------------------------------------------------------------
    // State Models
    // -------------------------------------------------------------------------
    sealed class SummaryState {
        object Idle : SummaryState()
        object Loading : SummaryState()
        data class Success(val summary: String) : SummaryState()
        data class Error(val message: String) : SummaryState()
    }

    sealed class QuestionsState {
        object Idle : QuestionsState()
        object Loading : QuestionsState()
        data class Success(val questions: List<QuestionAnswer>) : QuestionsState()
        data class Error(val message: String) : QuestionsState()
    }

    sealed class CheckAnswerState {
        object Idle : CheckAnswerState()
        object Loading : CheckAnswerState()
        data class Success(val result: CheckAnswerResponse) : CheckAnswerState()
        data class Error(val message: String) : CheckAnswerState()
    }

    // -------------------------------------------------------------------------
    // CRUD helpers – run on the IO dispatcher via viewModelScope
    // -------------------------------------------------------------------------
    // Users
    fun insertUser(user: User) = viewModelScope.launch {
        userRepo.insert(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepo.delete(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        userRepo.update(user)
    }

    // Articles
    fun insertArticle(article: Article) = viewModelScope.launch {
        articleRepo.insert(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        articleRepo.delete(article)
    }

    fun updateArticle(article: Article) = viewModelScope.launch {
        articleRepo.update(article)
    }

    // Prompts
    fun insertPrompt(prompt: Prompt) = viewModelScope.launch {
        promptRepo.insert(prompt)
    }

    fun deletePrompt(prompt: Prompt) = viewModelScope.launch {
        promptRepo.delete(prompt)
    }

    fun updatePrompt(prompt: Prompt) = viewModelScope.launch {
        promptRepo.update(prompt)
    }

    // Streaks
    fun insertStreak(streak: Streak) = viewModelScope.launch {
        streakRepo.insert(streak)
    }

    fun deleteStreak(streak: Streak) = viewModelScope.launch {
        streakRepo.delete(streak)
    }

    fun updateStreak(streak: Streak) = viewModelScope.launch {
        streakRepo.update(streak)
    }

    // -------------------------------------------------------------------------
    // API Methods
    // -------------------------------------------------------------------------
    fun summarizeArticle(text: String) = viewModelScope.launch {
        _summarizeState.value = SummaryState.Loading
        val result = aiLearningRepo.summarizeArticle(text)
        _summarizeState.value = result.fold(
            onSuccess = { summary -> SummaryState.Success(summary) },
            onFailure = { error -> SummaryState.Error(error.message ?: "Unknown error") }
        )
    }

    fun generateQuestions(text: String) = viewModelScope.launch {
        _questionsState.value = QuestionsState.Loading
        val result = aiLearningRepo.generateQuestions(text)
        _questionsState.value = result.fold(
            onSuccess = { questions -> QuestionsState.Success(questions) },
            onFailure = { error -> QuestionsState.Error(error.message ?: "Unknown error") }
        )
    }

    fun checkAnswer(question: String, userAnswer: String, aiAnswer: String) = viewModelScope.launch {
        _checkAnswerState.value = CheckAnswerState.Loading
        val result = aiLearningRepo.checkAnswer(question, userAnswer, aiAnswer)
        _checkAnswerState.value = result.fold(
            onSuccess = { response -> CheckAnswerState.Success(response) },
            onFailure = { error -> CheckAnswerState.Error(error.message ?: "Unknown error") }
        )
    }

    fun setCurrentQuestion(question: QuestionAnswer?) {
        _currentQuestion.value = question
        // Also try to find and set the index in the current questions list
        if (question != null) {
            val questionsState = _questionsState.value
            if (questionsState is QuestionsState.Success) {
                val index = questionsState.questions.indexOfFirst { it.question == question.question }
                if (index >= 0) {
                    _currentQuestionIndex.value = index
                }
            }
        }
    }

    fun setCurrentQuestionByIndex(index: Int) {
        val questionsState = _questionsState.value
        if (questionsState is QuestionsState.Success && index in questionsState.questions.indices) {
            _currentQuestionIndex.value = index
            _currentQuestion.value = questionsState.questions[index]
            // Reset check answer state for new question
            _checkAnswerState.value = CheckAnswerState.Idle
        }
    }

    fun setCurrentArticle(article: Article?) {
        _currentArticle.value = article
    }

    fun advanceToNextQuestion() {
        val questionsState = _questionsState.value
        if (questionsState is QuestionsState.Success) {
            val questions = questionsState.questions
            val nextIndex = _currentQuestionIndex.value + 1
            if (nextIndex < questions.size) {
                _currentQuestionIndex.value = nextIndex
                _currentQuestion.value = questions[nextIndex]
                // Reset check answer state for new question
                _checkAnswerState.value = CheckAnswerState.Idle
            }
        }
    }

    fun hasMoreQuestions(): Boolean {
        val questionsState = _questionsState.value
        return if (questionsState is QuestionsState.Success) {
            _currentQuestionIndex.value + 1 < questionsState.questions.size
        } else {
            false
        }
    }

    fun resetQuestions() {
        _currentQuestion.value = null
        _currentQuestionIndex.value = -1
        _questionsState.value = QuestionsState.Idle
        _checkAnswerState.value = CheckAnswerState.Idle
    }

    // -------------------------------------------------------------------------
    // fetch helpers (suspend functions)
    // -------------------------------------------------------------------------
    suspend fun getUserById(id: Int): User? = userRepo.getById(id)
    suspend fun getArticleById(id: Int): Article? = articleRepo.getById(id)
    suspend fun getArticlesByUserId(id: Int): Flow<List<Article>>? = articleRepo.getByUserId(id)
    suspend fun getPromptById(id: Int): Prompt? = promptRepo.getById(id)
    suspend fun getPromptsByArticleId(id: Int): Flow<List<Prompt>>? = promptRepo.getByArticleId(id)
    suspend fun getStreakById(id: Int): Streak? = streakRepo.getById(id)
}