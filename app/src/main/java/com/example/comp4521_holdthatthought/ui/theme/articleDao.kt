package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getById(id: Int): Article?

    @Query("SELECT * FROM articles WHERE userId = :id")
    fun getByUserId(id: Int): Flow<List<Article>>?

    @Insert
    suspend fun insert(article: Article)

    @Update
    suspend fun update(article: Article)

    @Delete
    suspend fun delete(article: Article)


    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}