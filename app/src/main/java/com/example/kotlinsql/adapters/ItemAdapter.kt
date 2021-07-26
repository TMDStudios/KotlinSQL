package com.example.kotlinsql.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsql.R
import com.example.kotlinsql.database.DataModel
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(
    private val context: Context,
    private val items: ArrayList<DataModel>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.apply {
            tvItem.text = item.itemText
            if(position%2==0){llItemHolder.setBackgroundColor(Color.GRAY)}
            ibDeleteItem.setOnClickListener { items.remove(items[position]) }
        }
    }

    override fun getItemCount() = items.size
}