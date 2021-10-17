package com.example.kotlinsql.data

class NoteRepository(private val noteDao: NoteDao) {

    val getNotes: List<Note> = noteDao.getNotes()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

}