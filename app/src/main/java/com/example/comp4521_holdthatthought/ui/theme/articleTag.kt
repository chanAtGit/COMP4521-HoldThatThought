package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "articleTags", foreignKeys = [ForeignKey(
    entity = Prompt::class,
    parentColumns = ["articleId"],
    childColumns = ["articleId"],
    onDelete = ForeignKey.CASCADE
)])
data class ArticleTag(
    @PrimaryKey val articleId: String,
    @PrimaryKey val tagId: String
)
