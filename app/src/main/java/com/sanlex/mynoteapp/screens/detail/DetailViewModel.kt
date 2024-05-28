package com.sanlex.mynoteapp.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sanlex.mynoteapp.database.NotesDatabase
import com.sanlex.mynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var db: NotesDatabase = NotesDatabase.getInstance(application)

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            db.getDao().updateNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            db.getDao().deleteNote(note)
        }
    }
}