package com.sanlex.mynoteapp.screens.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sanlex.mynoteapp.database.NotesDatabase
import com.sanlex.mynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var db = NotesDatabase.getInstance(application)

    fun getAllNotes() : LiveData<List<Note>>{
        return db.getDao().getAllNotes()
    }

    fun clearAllNotes(){
        viewModelScope.launch(Dispatchers.IO){
            db.getDao().clearNotes()
        }
    }
}