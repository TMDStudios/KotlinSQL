package com.example.kotlinsql.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinsql.data.Note
import com.example.kotlinsql.data.NoteDatabase
import com.example.kotlinsql.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// We use AndroidViewModel in order to get access to our application, which we pass into our getDatabase function
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val allNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.getNotes
    }

    fun getNotes(): LiveData<List<Note>>{
        return allNotes
    }

    fun addNote(noteText: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(Note(0, noteText))
        }
    }

    fun editNote(noteID: Int, noteText: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(Note(noteID,noteText))
        }
    }

    fun deleteNote(noteID: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(Note(noteID,""))
        }
    }
}