package com.example.kotlinsql.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsql.MainActivity
import com.example.kotlinsql.data.Note

import com.example.kotlinsql.databinding.NoteRowBinding

class NoteAdapter(
    private val activity: MainActivity,
    ): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {

    private var items = emptyList<Note>()

    class ItemViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ItemViewHolder {
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                activity.noteViewModel.deleteNote(item.id)
            }
        }
    }

    override fun getItemCount() = items.size

    fun update(notes: List<Note>){
        items = notes
        notifyDataSetChanged()
    }
}