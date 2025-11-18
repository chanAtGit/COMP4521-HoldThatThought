package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "streaks", foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE
)])
data class Streak(
    @PrimaryKey val userId: Int,
    val currentStreak: Int,
    val maxStreak:Int,
    val lastReadDate:Int,
    val streakFreezeCount:Long
)
