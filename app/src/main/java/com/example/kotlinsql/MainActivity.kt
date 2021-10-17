package com.example.kotlinsql

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsql.adapters.NoteAdapter
import com.example.kotlinsql.data.Note
import com.example.kotlinsql.data.NoteDatabase
import com.example.kotlinsql.data.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val noteDao by lazy { NoteDatabase.getDatabase(this).noteDao() }
    private val repository by lazy { NoteRepository(noteDao) }

    private lateinit var rvNotes: RecyclerView
    private lateinit var editText: EditText
    private lateinit var submitBtn: Button

    private lateinit var notes: List<Note>

    /**
     * This project makes Room work without a View Model, the Recycler View is only updated
     * each time the screen is rotated. Feel free to experiment with better functionality until
     * we cover View Models and Live Data, which will allow us to update the Recycler View each
     * time we make a change to our database.
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notes = listOf()

        editText = findViewById(R.id.tvNewNote)
        submitBtn = findViewById(R.id.btSubmit)
        submitBtn.setOnClickListener {
            viewModel.postNote(editText.text.toString())
            editText.text.clear()
            editText.clearFocus()
            updateRV()
        }

        getItemsList()

        rvNotes = findViewById(R.id.rvNotes)
        updateRV()
    }

    private fun updateRV(){
        rvNotes.adapter = NoteAdapter(this, notes)
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    private fun getItemsList(){
        CoroutineScope(IO).launch {
            val data = async {
                repository.getNotes
            }.await()
            if(data.isNotEmpty()){
                notes = data
                updateRV()
            }else{
                Toast.makeText(this@MainActivity, "Unable to get data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun postNote(){
        CoroutineScope(IO).launch {
            repository.addNote(Note(0,editText.text.toString()))
        }

        editText.text.clear()
        updateRV()
    }

    private fun editNote(noteID: Int, noteText: String){
        updateRV()
    }

    fun deleteNote(noteID: Int){
        updateRV()
    }

    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                // The setPositiveButton method takes in two arguments
                // More info here: https://developer.android.com/reference/kotlin/android/app/AlertDialog.Builder#setpositivebutton
                // Use underscores when lambda arguments are not used
                    _, _ ->
                run {
                    viewModel.editNote(id, updatedNote.text.toString())
                    updateRV()
                }
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