package com.example.comp4521_holdthatthought.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
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

    // -------------------------------------------------------------------------
    // Exposed Flows (read‑only)
    // -------------------------------------------------------------------------
    val allUsers: Flow<List<User>> = userRepo.allItems
    val allArticles: Flow<List<Article>> = articleRepo.allItems
    val allPrompts: Flow<List<Prompt>> = promptRepo.allItems
    val allStreaks: Flow<List<Streak>> = streakRepo.allItems

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
    // Optional: single‑item fetch helpers (suspend functions)
    // -------------------------------------------------------------------------
    suspend fun getUserById(id: String): User? = userRepo.getById(id)
    suspend fun getArticleById(id: String): Article? = articleRepo.getById(id)
    suspend fun getPromptById(id: String): Prompt? = promptRepo.getById(id)
    suspend fun getStreakById(id: String): Streak? = streakRepo.getById(id)
}