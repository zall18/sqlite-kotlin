package com.example.booklibrarysqlite

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.booklibrarysqlite.databinding.ActivityViewBookBinding
import drawable.DBHelper

class ViewBook : AppCompatActivity() {

    private lateinit var db: DBHelper
    private lateinit var bookAdapter: bookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_book)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var listview: ListView = findViewById(R.id.listview)
        db = DBHelper(this)
        bookAdapter = bookAdapter(this, db.getAllBooks())
        listview.adapter = bookAdapter
    }

    override fun onResume() {
        super.onResume()
        bookAdapter = bookAdapter(this, db.getAllBooks())

    }
}