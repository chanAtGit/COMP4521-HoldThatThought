package com.example.comp4521_holdthatthought.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: String,

    val email:String,
    val preference:String,
    val totalPoints:Int,
    val lastSyncDate:Long
)
