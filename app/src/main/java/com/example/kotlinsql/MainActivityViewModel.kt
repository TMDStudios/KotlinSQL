package com.example.kotlinsql

import androidx.lifecycle.ViewModel
import com.example.kotlinsql.database.DatabaseHandler
import com.example.kotlinsql.database.NoteModel

class MainActivityViewModel(private val db: DatabaseHandler): ViewModel() {

    fun getItemsList(): ArrayList<NoteModel>{
        return db.viewNotes()
    }
}