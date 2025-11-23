package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

enum class Type { //need to be more detailed
    ENTERTAINMENT,
    SCIENCE,
}

@Entity(tableName = "prompts", foreignKeys = [ForeignKey(
    entity = Article::class,
    parentColumns = ["id"],
    childColumns = ["articleId"],
    onDelete = ForeignKey.CASCADE
)])
data class Prompt(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val articleId: Int,
    val type: Type,
    val question: String,
    val answer: String, // Correct answer from AI
    val userAnswer: String? = null, // User's provided answer
    val isCorrect: Boolean? = null, // Result of validation
    val feedback: String? = null, // Feedback from AI
    val completed: Boolean = false,
    val generatedDate: Long,
)
