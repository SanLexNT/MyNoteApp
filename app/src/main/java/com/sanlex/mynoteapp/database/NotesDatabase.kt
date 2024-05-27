package com.sanlex.mynoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sanlex.mynoteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getDao(): NotesDao

    companion object {
        private var instance: NotesDatabase? = null
        private const val DB_NAME = "notes.db"

        @Synchronized
        fun getInstance(context: Context): NotesDatabase {
            instance?.let { return it }
            val database = Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME).build()
            instance = database
            return database
        }

    }
}