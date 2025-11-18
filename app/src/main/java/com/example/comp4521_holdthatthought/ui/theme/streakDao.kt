package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface StreakDao {
    @Query("SELECT * FROM streaks")
    fun getAll(): Flow<List<Streak>>

    @Query("SELECT * FROM streaks WHERE userId = :id")
    suspend fun getById(id: String): Streak?

    @Insert
    suspend fun insert(streak: Streak)

    @Update
    suspend fun update(streak: Streak)

    @Delete
    suspend fun delete(streak: Streak)

    @Query("DELETE FROM streaks")
    suspend fun deleteAll()
}