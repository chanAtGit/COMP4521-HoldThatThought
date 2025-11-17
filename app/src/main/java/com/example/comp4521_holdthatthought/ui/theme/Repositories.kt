package com.example.comp4521_holdthatthought.ui.theme

import kotlinx.coroutines.flow.Flow


class UserRepo(private val dao: UserDao) {

    /** Live list of all tasks. */
    val allItems: Flow<List<User>> = dao.getAll()

    suspend fun insert(user: User) = dao.insert(user)

    suspend fun delete(user: User) = dao.delete(user)

    suspend fun update(user: User) = dao.update(user)

    suspend fun getById(id: String): User? = dao.getById(id)
}

class ArticleRepo(private val dao: ArticleDao) {

    /** Live list of all tasks. */
    val allItems: Flow<List<Article>> = dao.getAll()

    suspend fun insert(article: Article) = dao.insert(article)

    suspend fun delete(article: Article) = dao.delete(article)

    suspend fun update(article: Article) = dao.update(article)

    suspend fun getById(id: String): Article? = dao.getById(id)
}

class PromptRepo(private val dao: PromptDao) {
    /** Live list of all tasks. */
    val allItems: Flow<List<Prompt>> = dao.getAll()

    suspend fun insert(prompt: Prompt) = dao.insert(prompt)

    suspend fun delete(prompt: Prompt) = dao.delete(prompt)

    suspend fun update(prompt: Prompt) = dao.update(prompt)

    suspend fun getById(id: String): Prompt? = dao.getById(id)
}

class StreakRepo(private val dao: StreakDao) {
    /** Live list of all tasks. */
    val allItems: Flow<List<Streak>> = dao.getAll()

    suspend fun insert(streak: Streak) = dao.insert(streak)

    suspend fun delete(streak: Streak) = dao.delete(streak)

    suspend fun update(streak: Streak) = dao.update(streak)

    suspend fun getById(id: String): Streak? = dao.getById(id)
}