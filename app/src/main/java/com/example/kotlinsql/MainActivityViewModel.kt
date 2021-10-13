package com.example.kotlinsql

import androidx.lifecycle.ViewModel
import com.example.kotlinsql.database.DatabaseHandler
import com.example.kotlinsql.database.NoteModel

class MainActivityViewModel(private val db: DatabaseHandler): ViewModel() {

    fun getNotesList(): ArrayList<NoteModel>{
        return db.viewNotes()
    }

    fun postNote(noteText: String){
        // The id is automatically generated
        db.addNote(NoteModel(0, noteText))
    }

    fun editNote(noteID: Int, noteText: String){
        db.updateNote(NoteModel(noteID, noteText))
    }

    fun deleteNote(noteID: Int){
        db.deleteNote(NoteModel(noteID, ""))
    }
}