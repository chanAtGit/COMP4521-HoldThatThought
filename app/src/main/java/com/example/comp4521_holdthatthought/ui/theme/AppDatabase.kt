package com.example.comp4521_holdthatthought.ui.theme

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlin.jvm.java

/**
 * The Room database that holds the Task table.
 */
@Database(entities = [User::class, Article::class, Streak::class, Prompt::class], version = 1, exportSchema = false)
//@TypeConverters(UriConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun articleDao(): ArticleDao
    abstract fun streakDao(): StreakDao
    abstract fun promptDao(): PromptDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "holdThatThought-db"
                )
                    // For simple apps you can allow main‑thread queries,
                    // but it's better to keep it false (default) and use coroutines.
                    .fallbackToDestructiveMigration() // handle version upgrades simply
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}