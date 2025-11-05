package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tags")
data class Tags(
    @PrimaryKey val id: String,

    val userId: String,
    val name: String,
)
