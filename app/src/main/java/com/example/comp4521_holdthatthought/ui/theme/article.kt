package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Status {
    READING,
    COMPLETED
}

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey val id: String,

    val userId: String,
    val title: String,
    val url: String,
    val content: String,
    val addedDate: Long,
    val readStatus: Status,
    val progress:Float,
    val readDate:Long
)
