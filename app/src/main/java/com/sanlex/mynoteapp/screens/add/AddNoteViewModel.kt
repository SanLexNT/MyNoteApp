package com.sanlex.mynoteapp.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sanlex.mynoteapp.database.NotesDatabase
import com.sanlex.mynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private var db = NotesDatabase.getInstance(application)

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            db.getDao().insertNote(note)
        }
    }
}