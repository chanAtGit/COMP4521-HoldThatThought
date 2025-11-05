package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streaks")
data class Streak(
    @PrimaryKey val userId: String,

    val currentStreak: Int,
    val maxStreak:Int,
    val lastReadDate:Int,
    val streakFreezeCount:Long
)
