package com.example.kotlinsql

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinsql.database.DatabaseHandler
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val db: DatabaseHandler): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}