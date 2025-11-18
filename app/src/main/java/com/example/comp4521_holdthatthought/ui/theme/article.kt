package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

enum class Status {
    READING,
    COMPLETED
}

@Entity(tableName = "articles", foreignKeys = [ForeignKey(
    entity = Streak::class,
    parentColumns = ["userId"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE
)])
data class Article(
    @PrimaryKey(autoGenerate = true) val id: String,

    val userId: String,
    val title: String,
    val url: String,
    val content: String,
    val addedDate: Long,
    val readStatus: Status,
    val progress:Float,
    val readDate:Long
)
