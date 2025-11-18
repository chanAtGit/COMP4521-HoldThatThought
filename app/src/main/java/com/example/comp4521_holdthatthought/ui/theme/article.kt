package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

enum class Status {
    READING,
    COMPLETED
}

@Entity(tableName = "articles", foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE
)])
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val userId: Int,
    val title: String,
    val url: String,
    val content: String,
    val addedDate: Long,
    val readStatus: Status,
    val progress:Float,
    val readDate:Long,
    val tagId: String
)
