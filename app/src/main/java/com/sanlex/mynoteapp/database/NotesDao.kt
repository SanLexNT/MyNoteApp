package com.sanlex.mynoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sanlex.mynoteapp.model.Note

@Dao
interface NotesDao{
    @Query("SELECT * FROM notes")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id ==:idNote LIMIT 1")
    fun getNoteById(idNote: Int) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notes")
    fun clearNotes()

}