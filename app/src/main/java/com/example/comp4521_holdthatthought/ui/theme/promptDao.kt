package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface PromptDao {
    @Query("SELECT * FROM prompts")
    fun getAllPrompts(): Flow<List<Prompt>>

    @Query("SELECT * FROM prompts WHERE id = :id")
    suspend fun getPromptById(id: String): Prompt?

    @Insert
    suspend fun insertPrompt(prompt: Prompt)

    @Update
    suspend fun updatePrompt(prompt: Prompt)

    @Delete
    suspend fun deletePrompt(prompt: Prompt)

    @Query("DELETE FROM prompts")
    suspend fun deleteAllPrompts()
}