package com.example.kotlinsql

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsql.adapters.NoteAdapter
import com.example.kotlinsql.database.DatabaseHandler
import com.example.kotlinsql.database.NoteModel

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHandler
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var rvNotes: RecyclerView
    private lateinit var editText: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHandler(this)
        val viewModelFactory = MainActivityViewModelFactory(db)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        editText = findViewById(R.id.tvNewNote)
        submitBtn = findViewById(R.id.btSubmit)
        submitBtn.setOnClickListener { postNote() }

        rvNotes = findViewById(R.id.rvNotes)
        updateRV()
    }

    private fun updateRV(){
        rvNotes.adapter = NoteAdapter(this, viewModel.getItemsList())
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    private fun postNote(){
        db.addNote(NoteModel(0, editText.text.toString()))
        editText.text.clear()
        updateRV()
    }

    fun editNote(noteID: Int, noteText: String){
        db.updateNote(NoteModel(noteID, noteText))
        updateRV()
    }

    fun deleteNote(noteID: Int){
        db.deleteNote(NoteModel(noteID, ""))
        updateRV()
    }

    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }
}