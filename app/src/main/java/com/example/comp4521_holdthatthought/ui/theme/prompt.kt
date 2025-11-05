package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Type { //need to be more detailed
    ENTERTAINMENT,
    SCIENCE,
}

@Entity(tableName = "prompts")
data class Prompt(
    @PrimaryKey val id: String,

    val articleId: String,
    val type: Type,
    val question: String,
    val response: String,
    val completed: Boolean,
    val generatedDate: Long,
)
