package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articleTags")
data class ArticleTag(
    @PrimaryKey val articleId: String,
    @PrimaryKey val tagId: String
)
