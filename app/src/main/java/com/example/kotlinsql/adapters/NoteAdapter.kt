package com.example.kotlinsql.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsql.MainActivity
import com.example.kotlinsql.database.NoteModel
import com.example.kotlinsql.databinding.NoteRowBinding

class NoteAdapter(
    private val context: Context,
    private val items: ArrayList<NoteModel>): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {

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
                if(context is MainActivity){
                    (context as MainActivity).raiseDialog(item.id)
                }
            }
            ibDeleteNote.setOnClickListener {
                if(context is MainActivity){
                    (context as MainActivity).viewModel.deleteNote(item.id)
                    (context as MainActivity).updateRV()
                }
            }
        }
    }

    override fun getItemCount() = items.size
}