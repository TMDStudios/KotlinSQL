package com.example.kotlinsql.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsql.MainActivity
import com.example.kotlinsql.database.NoteModel
import com.example.kotlinsql.databinding.NoteRowBinding

class NoteAdapter(
    private val activity: MainActivity,
    private val items: ArrayList<NoteModel>): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.ItemViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            tvNote.text = item.noteText
            if(position%2==0){llNoteHolder.setBackgroundColor(Color.GRAY)}
            ibEditNote.setOnClickListener {
                activity.raiseDialog(item.id)
            }
            ibDeleteNote.setOnClickListener {
                activity.deleteNote(item.id)
            }
        }
    }

    override fun getItemCount() = items.size
}