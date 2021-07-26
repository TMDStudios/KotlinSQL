package com.example.kotlinsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinsql.database.DataModel
import com.example.kotlinsql.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.tvNewItem)
        submitBtn = findViewById(R.id.btSubmit)

//        rvItems.adapter = ItemAdapter(this, getItemsList())
//        rvItems.layoutManager = LinearLayoutManager(this)
    }

//    private fun getItemsList(): ArrayList<DataModel>{
//        val tempList: ArrayList<DataModel>
//        return tempList
//    }
}