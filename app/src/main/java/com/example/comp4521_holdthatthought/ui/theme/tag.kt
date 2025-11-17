package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "tags",  foreignKeys = [ForeignKey(
    entity = Article::class,
    parentColumns = ["id"],
    childColumns = ["id"],
    onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Article::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )])
data class Tags(
    @PrimaryKey val id: String,

    val userId: String,
    val name: String,
)
