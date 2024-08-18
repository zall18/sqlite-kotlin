package com.example.booklibrarysqlite

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.booklibrarysqlite.databinding.ActivityCreateBookBinding
import drawable.DBHelper
import drawable.bookModel

class CreateBook : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBookBinding
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = ActivityCreateBookBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_create_book)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = DBHelper(this)

        val create: AppCompatButton = findViewById(R.id.create_button)
        val name: EditText = findViewById(R.id.name_input)
        val author: EditText = findViewById(R.id.author_input)
        val publisher: EditText = findViewById(R.id.publisher_input)

        create.setOnClickListener {
            val books = bookModel(0, name.text.toString(), publisher.text.toString(), author.text.toString())
            db.CreateBook(books)
            finish()
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

    }
}